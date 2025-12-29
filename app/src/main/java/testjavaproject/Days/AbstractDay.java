package testjavaproject.Days;

import java.util.List;

import testjavaproject.InputReader;

public abstract class AbstractDay {

  /**
   * Day number used for input lookup.
   */
  protected abstract int dayNumber();

  protected abstract void part1(List<String> input) throws Exception;

  protected abstract void part2(List<String> input) throws Exception;

  public final void run() throws Exception {
    List<String> lines = InputReader.readAllLines(dayNumber());
    List<String> cleaned = lines.stream()
        .filter(s -> s != null && !s.isBlank())
        .map(String::trim)
        .toList();
    long t0 = System.nanoTime();
    part1(cleaned);
    long t1 = System.nanoTime();
    part2(cleaned);
    long t2 = System.nanoTime();
    System.out.println("Part1: " + ((t1 - t0) / 1_000_000) + " ms");
    System.out.println("Part2: " + ((t2 - t1) / 1_000_000) + " ms");
  }
}
