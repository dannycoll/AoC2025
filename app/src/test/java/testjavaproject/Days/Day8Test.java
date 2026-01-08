package testjavaproject.Days;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Test;

class Day8Test {

  private static final List<String> EXAMPLE = List.of(
      "162,817,812",
      "57,618,57",
      "906,360,560",
      "592,479,940",
      "352,342,300",
      "466,668,158",
      "542,29,236",
      "431,825,988",
      "739,650,466",
      "52,470,668",
      "216,146,977",
      "819,987,18",
      "117,168,530",
      "805,96,715",
      "346,949,466",
      "970,615,88",
      "941,993,340",
      "862,61,35",
      "984,92,344",
      "425,690,689");

  @Test
  void examplePart1_matches() {
    long result = Day8.solvePart1(EXAMPLE, 10);
    assertEquals(40, result,
        "After 10 shortest connections, product of three largest circuits should be 40");
  }

  @Test
  void examplePart2_matches() throws Exception {
    var out = captureOutput(() -> new Day8().part2(EXAMPLE));
    assertEquals("25272", out.trim(),
        "Product of X coordinates of last connection that forms single circuit should be 25272");
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
