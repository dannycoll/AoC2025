package testjavaproject.Days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

  @Test
  void parseMove_leftAndRight() {
    Day1.Move m1 = Day1.parseMove("L10");
    assertEquals(-1, m1.direction());
    assertEquals(10, m1.amount());

    Day1.Move m2 = Day1.parseMove("R5");
    assertEquals(1, m2.direction());
    assertEquals(5, m2.amount());
  }

  @Test
  void computeEnd_wrapsCorrectly() {
    assertEquals(1, Day1.computeEnd(0, 1, 1));
    assertEquals(0, Day1.computeEnd(98, 1, 2));
    assertEquals(99, Day1.computeEnd(0, -1, 1));
  }

  @Test
  void crossed_detection() {
    assertFalse(Day1.crossed(98, 1, 1)); // forward crossing
    assertTrue(Day1.crossed(2, -1, 3)); // backward crossing
    assertFalse(Day1.crossed(10, 1, 5));
  }

  @Test
  void applyMove_rotationsAndCrossing() {
    Day1.State start = new Day1.State(49, -1);

    // full rotation of exactly RING_SIZE increments password
    Day1.State s1 = Day1.applyMove(start, new Day1.Move(1, Day1.RING_SIZE), true);
    assertEquals(49, s1.position());
    assertEquals(0, s1.password());

    // crossing forward
    Day1.State s2 = Day1.applyMove(start, new Day1.Move(1, 50), true);
    assertEquals(99, s2.position());
    assertEquals(-1, s2.password());
  }

  @Test
  void exampleCountsThree() {
    Day1.State finalState = Day1TestHelpers.runExample(false);
    assertEquals(3, finalState.password());
  }

  @Test
  void exampleCountsSix() {
    Day1.State finalState = Day1TestHelpers.runExample(true);
    assertEquals(6, finalState.password());
  }
}
