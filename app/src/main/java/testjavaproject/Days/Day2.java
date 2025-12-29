package testjavaproject.Days;

import java.util.List;
import java.util.Arrays;
import java.util.function.LongPredicate;

public class Day2 extends AbstractDay {

  public static void main(String[] args) throws Exception {
    new Day2().run();
  }

  static boolean isInvalid1(String s) {
    int len = s.length();
    String firstHalf = s.substring(0, len / 2);
    String secondHalf = s.substring(len / 2);
    return firstHalf.equals(secondHalf);
  }

  static boolean isInvalid2(String s) {
    if (s == null || s.length() == 0)
      return false;

    String doubled = s + s;
    int idx = doubled.indexOf(s, 1);
    return idx > 0 && idx < s.length();
  }

  static long sumInvalids(List<String> input, LongPredicate pred) {
    return input.stream()
        .flatMap(line -> Arrays.stream(line.split(",")))
        .map(String::trim)
        .mapToLong(range -> Range.parse(range).sumIf(pred))
        .sum();
  }

  @Override
  protected int dayNumber() {
    return 2;
  }

  @Override
  protected void part1(List<String> input) throws Exception {
    long sum = sumInvalids(input, i -> isInvalid1(String.valueOf(i)));
    System.out.println(sum);
  }

  @Override
  protected void part2(List<String> input) throws Exception {
    long sum = sumInvalids(input, i -> isInvalid2(String.valueOf(i)));
    System.out.println(sum);
  }
}
