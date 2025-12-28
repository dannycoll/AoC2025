package testjavaproject.Days;

import java.util.List;

class Day1 extends AbstractDay {

  public static void main(String[] args) throws Exception {
    new Day1().run();
  }

  @Override
  protected int dayNumber() {
    return 1;
  }

  static final int RING_SIZE = 100;
  static final int LAST_INDEX = RING_SIZE - 1;

  static record State(int position, int password) {
  }

  static record Move(int direction, int amount) {
  }

  @Override
  protected void part1(List<String> input) {
    runDay(input, false);
  }

  @Override
  protected void part2(List<String> input) {
    runDay(input, true);
  }

  private void runDay(List<String> input, boolean includeFullRotations) {
    State init = new State(50, 0);

    State finalState = input.stream()
        .reduce(init, (st, s) -> applyMove(st, parseMove(s), includeFullRotations), (a, b) -> b);

    System.out.println(finalState.password);
  }

  static Move parseMove(String s) {
    char first = s.charAt(0);
    int direction = (first == 'L') ? -1 : 1;
    int amount = Integer.parseInt(s.substring(1));
    return new Move(direction, amount);
  }

  static int fullRotations(int amount) {
    return amount / RING_SIZE;
  }

  static int computeEnd(int start, int direction, int remainder) {
    int end = (start + direction * remainder) % RING_SIZE;
    if (end < 0)
      end += RING_SIZE;
    return end;
  }

  static boolean crossed(int start, int direction, int remainder) {
    if (start == 0)
      return false;
    if (direction == 1) {
      return remainder >= RING_SIZE - start;
    } else {
      return remainder >= start;
    }
  }

  static int zerosDuringRotation(int start, Move m) {
    int full = fullRotations(m.amount);
    int remainder = m.amount % RING_SIZE;
    int extra = crossed(start, m.direction, remainder) ? 1 : 0;
    return full + extra;
  }

  static State applyMove(State st, Move m, boolean includeFullRotations) {
    int start = st.position;
    int remainder = m.amount % RING_SIZE;
    int end = computeEnd(start, m.direction(), remainder);

    int extra;
    if (includeFullRotations) {
      int full = fullRotations(m.amount);
      int crossedExtra = crossed(start, m.direction, remainder) ? 1 : 0;
      extra = full + crossedExtra;
    } else {
      extra = (end == 0) ? 1 : 0;
    }

    int password = st.password + extra;
    return new State(end, password);
  }
}