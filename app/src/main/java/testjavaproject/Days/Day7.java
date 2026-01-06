package testjavaproject.Days;

import java.util.List;

public class Day7 extends AbstractDay {

  public static void main(String[] args) throws Exception {
    new Day7().run();
  }

  @Override
  protected int dayNumber() {
    return 7;
  }

  @Override
  protected void part1(List<String> input) throws Exception {
    var res = simulate(input, true);
    System.out.println(res.splits());
  }

  @Override
  protected void part2(List<String> input) throws Exception {
    var res = simulate(input, false);
    long total = 0L;
    for (long c : res.counts())
      total += c;
    System.out.println(total);
  }

  private static SimulationResult simulate(List<String> input, boolean classical) {
    long[] counts = null;
    boolean needFirst = true;
    long splits = 0L;

    for (String data : input) {
      char[] chars = data.toCharArray();
      int width = chars.length;

      if (needFirst) {
        counts = new long[width];
        int s = findS(chars);
        if (s >= 0) {
          counts[s] = 1;
          needFirst = false;
        }
      } else {
        long[] next = new long[width];
        for (int i = 0; i < width; i++) {
          if (counts[i] == 0)
            continue;
          if (chars[i] == '^') {
            if (classical) {
              splits += 1L;
              if (i - 1 >= 0)
                next[i - 1] = 1L;
              if (i + 1 < width)
                next[i + 1] = 1L;
            } else {
              if (i - 1 >= 0)
                next[i - 1] += counts[i];
              if (i + 1 < width)
                next[i + 1] += counts[i];
            }
          } else {
            if (classical)
              next[i] = 1L;
            else
              next[i] += counts[i];
          }
        }
        counts = next;
      }
    }

    return new SimulationResult(counts == null ? new long[0] : counts, splits);
  }

  private static int findS(char[] chars) {
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == 'S')
        return i;
    }
    return -1;
  }

  private static record SimulationResult(long[] counts, long splits) {
  }

}
