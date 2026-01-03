package testjavaproject.Days;

import java.util.List;
import java.util.stream.Collectors;

import testjavaproject.Range;

public class Day5 extends AbstractDay {

  public static void main(String[] args) throws Exception {
    new Day5().run();
  }

  @Override
  protected int dayNumber() {
    return 5;
  }

  @Override
  protected void part1(List<String> input) throws Exception {
    List<Range> ranges = parseRanges(input);
    List<Range> merged = Range.mergeRanges(ranges);

    long count = input.stream()
        .map(String::trim)
        .dropWhile(s -> !s.isEmpty())
        .skip(1)
        .filter(s -> !s.isEmpty())
        .mapToLong(Long::parseLong)
        .filter(id -> merged.stream().anyMatch(r -> r.contains(id)))
        .count();

    System.out.println(count);
  }

  @Override
  protected void part2(List<String> input) throws Exception {
    List<Range> ranges = parseRanges(input);
    long total = Range.mergeRanges(ranges).stream()
        .mapToLong(r -> (r.end - r.start + 1))
        .sum();

    System.out.println(total);
  }

  static List<Range> parseRanges(List<String> input) {
    return input.stream()
        .map(String::trim)
        .takeWhile(s -> !s.isEmpty())
        .filter(s -> !s.isEmpty())
        .map(Range::parse)
        .collect(Collectors.toList());
  }
}
