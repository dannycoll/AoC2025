package testjavaproject.Days;

import java.util.List;

class Day1TestHelpers {

  static List<String> exampleLines() {
    return List.of(
        "L68", "L30", "R48", "L5", "R60", "L55", "L1", "L99", "R14", "L82");
  }

  static Day1.State runExample(boolean includeFullRotations) {
    var lines = exampleLines();
    Day1.State init = new Day1.State(50, 0);
    return lines.stream()
        .filter(s -> !s.isBlank())
        .map(String::trim)
        .reduce(init, (st, s) -> Day1.applyMove(st, Day1.parseMove(s), includeFullRotations), (a, b) -> b);
  }
}
