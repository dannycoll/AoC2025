package testjavaproject.Days;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Test;

class Day9Test {

  private static final List<String> EXAMPLE = List.of(
      "7,1",
      "11,1",
      "11,7",
      "9,7",
      "9,5",
      "2,5",
      "2,3",
      "7,3"
  );

  @Test
  void examplePart1_matches() throws Exception {
    var out = captureOutput(() -> new Day9().part1(EXAMPLE));
    assertEquals("50", out.trim(),
        "Largest rectangle area should be 50");
  }

  @Test
  void examplePart2_matches() throws Exception {
    var out = captureOutput(() -> new Day9().part2(EXAMPLE));
    // Part2 outputs two lines: perimeter size and max area
    var lines = out.trim().split("\n");
    assertEquals(2, lines.length, "Part2 should output 2 lines");
    assertEquals("24", lines[1].trim(),
        "Largest rectangle area using only red and green tiles should be 24");
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
