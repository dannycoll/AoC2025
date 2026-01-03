package testjavaproject.Days;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Test;
import testjavaproject.Range;

class Day5Test {

  private static final List<String> EXAMPLE = List.of(
      "3-5",
      "10-14",
      "16-20",
      "12-18",
      "",
      "1",
      "5",
      "8",
      "11",
      "17",
      "32");

  @Test
  void examplePart1_counts3() throws Exception {
    var out = captureOutput(() -> new Day5().part1(EXAMPLE));
    assertEquals("3", out.trim());
  }

  @Test
  void examplePart2_counts14() throws Exception {
    var out = captureOutput(() -> new Day5().part2(EXAMPLE));
    assertEquals("14", out.trim());
  }

  @Test
  void parseRanges_example() {
    List<String> input = List.of(
        "3-5",
        "10-14",
        "16-20",
        "12-18",
        "",
        "1",
        "5");

    var ranges = Day5.parseRanges(input);
    assertEquals(4, ranges.size());
    assertEquals(3, ranges.get(0).start);
    assertEquals(5, ranges.get(0).end);
    assertEquals(10, ranges.get(1).start);
    assertEquals(14, ranges.get(1).end);
    assertEquals(16, ranges.get(2).start);
    assertEquals(20, ranges.get(2).end);
    assertEquals(12, ranges.get(3).start);
    assertEquals(18, ranges.get(3).end);
  }

  @Test
  void parseRanges_noBlankLine() {
    List<String> input = List.of("1-2", "3-4");
    var ranges = Day5.parseRanges(input);
    assertEquals(2, ranges.size());
    assertEquals(1, ranges.get(0).start);
    assertEquals(2, ranges.get(0).end);
  }

  @Test
  void parseRanges_initialBlankLine() {
    List<String> input = List.of("", "1", "2");
    var ranges = Day5.parseRanges(input);
    assertTrue(ranges.isEmpty());
  }

  private static String captureOutput(CheckedRunnable r) throws Exception {
    var baos = new ByteArrayOutputStream();
    var ps = new PrintStream(baos);
    var old = System.out;
    System.setOut(ps);
    try {
      r.run();
    } finally {
      System.setOut(old);
    }
    return baos.toString();
  }

  @FunctionalInterface
  interface CheckedRunnable {
    void run() throws Exception;
  }
}
