package testjavaproject.Days;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Test;

class Day7Test {

  private static final List<String> EXAMPLE = List.of(
      ".......S.......",
      "...............",
      ".......^.......",
      "...............",
      "......^.^......",
      "...............",
      ".....^.^.^.....",
      "...............",
      "....^.^...^....",
      "...............",
      "...^.^...^.^...",
      "...............",
      "..^...^.....^..",
      "...............",
      ".^.^.^.^.^...^.",
      "..............."
  );

  @Test
  void examplePart1_matches() throws Exception {
    var out = captureOutput(() -> new Day7().part1(EXAMPLE));
    assertEquals("21", out.trim());
  }

  @Test
  void examplePart2_matches() throws Exception {
    var out = captureOutput(() -> new Day7().part2(EXAMPLE));
    assertEquals("40", out.trim());
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