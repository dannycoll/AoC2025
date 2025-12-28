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
    part1(cleaned);
    part2(cleaned);
  }
}
