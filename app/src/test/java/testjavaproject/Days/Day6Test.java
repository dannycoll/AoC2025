package testjavaproject.Days;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Test;

class Day6Test {

  private static final List<String> EXAMPLE = List.of(
      "123 328  51 64 ",
      " 45 64  387 23 ",
      "  6 98  215 314",
      "*   +   *   +  ");

  @Test
  void examplePart1_matches() throws Exception {
    var out = captureOutput(() -> new Day6().part1(EXAMPLE));
    assertEquals("4277556", out.trim());
  }

  @Test
  void examplePart2_matches() throws Exception {
    var out = captureOutput(() -> new Day6().part2(EXAMPLE));
    assertEquals("3263827", out.trim());
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
