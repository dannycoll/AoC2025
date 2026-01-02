package testjavaproject.Days;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import testjavaproject.GridUtils;

public class Day4 extends AbstractDay {

  public static void main(String[] args) throws Exception {
    new Day4().run();
  }

  @Override
  protected int dayNumber() {
    return 4;
  }

  @Override
  protected void part1(List<String> input) throws Exception {
    var padded = GridUtils.padRight(input);
    char[][] grid = GridUtils.toCharGrid(padded);
    final char[][] current = grid;
    long count = IntStream.range(0, current.length)
        .mapToLong(i -> IntStream.range(0, current[0].length)
            .filter(j -> current[i][j] == '@' && GridUtils.countAdjacent(current, i, j, '@') < 4)
            .count())
        .sum();
    System.out.println(count);
  }

  @Override
  protected void part2(List<String> input) throws Exception {
    var padded = GridUtils.padRight(input);
    char[][] grid = GridUtils.toCharGrid(padded);
    // make a mutable copy
    char[][] copyGrid = GridUtils.copyGrid(grid);
    int totalRemoved = 0;
    int removed;
    do {
      final char[][] current = grid;
      final char[][] next = copyGrid;
      List<int[]> toRemove = new ArrayList<>();
      GridUtils.forEachCell(current, (i, j) -> {
        if (current[i][j] == '@' && GridUtils.countAdjacent(current, i, j, '@') < 4)
          toRemove.add(new int[] { i, j });
      });

      removed = toRemove.size();
      totalRemoved += removed;
      toRemove.forEach(p -> next[p[0]][p[1]] = '.');

      // update grid and copyGrid for next iteration
      grid = GridUtils.copyGrid(next);
      copyGrid = GridUtils.copyGrid(grid);
    } while (removed > 0);

    System.out.println(totalRemoved);
  }
}
