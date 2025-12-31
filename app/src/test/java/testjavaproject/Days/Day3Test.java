package testjavaproject.Days;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class Day3Test {

  private static final List<String> EXAMPLE = List.of(
      "987654321111111",
      "811111111111119",
      "234234234234278",
      "818181911112111");

  @Test
  void example_part1_perBank() {
    assertEquals(98, Day3.getJoltage(EXAMPLE.get(0), 2));
    assertEquals(89, Day3.getJoltage(EXAMPLE.get(1), 2));
    assertEquals(78, Day3.getJoltage(EXAMPLE.get(2), 2));
    assertEquals(92, Day3.getJoltage(EXAMPLE.get(3), 2));
  }

  @Test
  void example_part1_total() {
    long total = EXAMPLE.stream().mapToLong(s -> Day3.getJoltage(s, 2)).sum();
    assertEquals(357, total);
  }

  @Test
  void example_part2_perBank() {
    assertEquals(987654321111L, Day3.getJoltage(EXAMPLE.get(0), 12));
    assertEquals(811111111119L, Day3.getJoltage(EXAMPLE.get(1), 12));
    assertEquals(434234234278L, Day3.getJoltage(EXAMPLE.get(2), 12));
    assertEquals(888911112111L, Day3.getJoltage(EXAMPLE.get(3), 12));
  }

  @Test
  void example_part2_total() {
    long total = EXAMPLE.stream().mapToLong(s -> Day3.getJoltage(s, 12)).sum();
    assertEquals(3121910778619L, total);
  }
}
