package testjavaproject.Days;

import java.util.List;

public class Day3 extends AbstractDay {

  public static void main(String[] args) throws Exception {
    new Day3().run();
  }

  @Override
  protected int dayNumber() {
    return 3;
  }

  @Override
  protected void part1(List<String> input) throws Exception {
    long sum = input.stream().mapToLong(s -> getJoltage(s, 2)).sum();
    System.out.println(sum);
  }

  @Override
  protected void part2(List<String> input) throws Exception {
    long sum = input.stream().mapToLong(s -> getJoltage(s, 12)).sum();
    System.out.println(sum);
  }

  static long getJoltage(String bankString, int numDigits) {
    int drop = bankString.length() - numDigits;
    StringBuilder result = new StringBuilder(Math.max(numDigits, 0));
    for (char c : bankString.toCharArray()) {
      while (result.length() > 0 && drop > 0 && result.charAt(result.length() - 1) < c) {
        result.deleteCharAt(result.length() - 1);
        drop--;
      }
      result.append(c);
    }
    return Long.parseLong(result.substring(0, numDigits));
  }
}
