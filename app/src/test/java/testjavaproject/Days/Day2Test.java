package testjavaproject.Days;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class Day2Test {

  @Test
  void isInvalid1_basic() {
    assertTrue(Day2.isInvalid1("1212"));
    assertTrue(Day2.isInvalid1("11"));
    assertFalse(Day2.isInvalid1("1234"));
  }

  @Test
  void isInvalid2_basic() {
    assertTrue(Day2.isInvalid2("12341234"));
    assertTrue(Day2.isInvalid2("1212"));
    assertTrue(Day2.isInvalid2("1111111"));
    assertFalse(Day2.isInvalid2("1231"));
  }

  @Test
  void sumInvalids_examples() {
    List<String> input = List.of("11-22", "95-115");
    long sum = Day2.sumInvalids(input, i -> Day2.isInvalid2(String.valueOf(i)));
    // 11+22 = 33; 99+111 = 210; total = 243
    assertEquals(243, sum);
  }
}
