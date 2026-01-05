package testjavaproject.Days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import testjavaproject.GridUtils;

public class Day6 extends AbstractDay {

  public static void main(String[] args) throws Exception {
    new Day6().run();
  }

  @Override
  protected int dayNumber() {
    return 6;
  }

  @Override
  protected void part1(List<String> input) throws Exception {
    long total = computeTotal(input, false);
    System.out.println(total);
  }

  @Override
  protected void part2(List<String> input) throws Exception {
    long total = computeTotal(input, true);
    System.out.println(total);
  }

  static long computeTotal(List<String> linesRaw, boolean rightToLeft) {
    var views = getProblemViews(linesRaw, rightToLeft);
    if (views.isEmpty())
      return 0L;

    return views.stream().mapToLong(Day6::processView).sum();
  }

  static long processView(ProblemView view) {
    long[] nums = view.nums().stream().mapToLong(Long::parseLong).toArray();
    return view.op() == '+' ? Arrays.stream(nums).sum() : Arrays.stream(nums).reduce(1L, (a, b) -> a * b);
  }

  static List<ProblemView> getProblemViews(List<String> linesRaw, boolean rightToLeft) {
    var padded = GridUtils.padRight(linesRaw);
    char[][] grid = GridUtils.toCharGrid(padded);
    int rows = grid.length;
    int cols = grid[0].length;

    List<String> colStr = IntStream.range(0, cols)
        .mapToObj(cIdx -> IntStream.range(0, rows - 1).mapToObj(r -> String.valueOf(grid[r][cIdx]))
            .collect(Collectors.joining()))
        .collect(Collectors.toList());
    char[] bottom = new char[cols];
    for (int i = 0; i < cols; i++)
      bottom[i] = grid[rows - 1][i];

    List<List<Integer>> problems = new ArrayList<>();
    int c = 0;
    while (c < cols) {
      if (GridUtils.isSeparatorColumn(grid, c)) {
        c++;
        continue;
      }
      List<Integer> group = new ArrayList<>();
      while (c < cols && !GridUtils.isSeparatorColumn(grid, c)) {
        group.add(c);
        c++;
      }
      problems.add(group);
    }

    return problems.stream().map(group -> {
      List<String> nums;
      if (!rightToLeft) {
        nums = IntStream.range(0, rows - 1)
            .mapToObj(r -> group.stream().map(ci -> String.valueOf(grid[r][ci])).collect(Collectors.joining()).trim())
            .filter(s -> !s.isEmpty())
            .collect(Collectors.toList());
      } else {
        List<Integer> order = new ArrayList<>(group);
        Collections.reverse(order);
        nums = order.stream().map(ci -> colStr.get(ci).replaceAll("\\D+", ""))
            .filter(s -> !s.isEmpty())
            .collect(Collectors.toList());
      }

      char op = group.stream().map(ci -> bottom[ci]).filter(ch -> ch != ' ').findFirst().orElse((char) ' ');
      return new ProblemView(nums, op);
    }).collect(Collectors.toList());
  }

  static record ProblemView(List<String> nums, char op) {
  }

}
