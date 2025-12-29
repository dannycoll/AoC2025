package testjavaproject;

public class Range {
  public long start;
  public long end;

  public Range(String input) {
    var parts = input.split("-");
    this.start = Long.parseLong(parts[0]);
    this.end = Long.parseLong(parts[1]);
  }

  public Range(long start, long end) {
    this.start = start;
    this.end = end;
  }

  public boolean contains(Long id) {
    return id >= this.start && id <= this.end;
  }

  public static java.util.List<Range> mergeRanges(java.util.List<Range> ranges) {
    java.util.List<Range> out = new java.util.ArrayList<>();
    if (ranges == null || ranges.isEmpty())
      return out;

    java.util.List<Range> copy = new java.util.ArrayList<>(ranges);
    copy.sort(java.util.Comparator.comparingLong(r -> r.start));

    Range prev = copy.get(0);
    for (int i = 1; i < copy.size(); i++) {
      Range curr = copy.get(i);
      if (curr.start <= prev.end + 1) {
        // merge
        prev = new Range(prev.start, Math.max(prev.end, curr.end));
      } else {
        out.add(prev);
        prev = curr;
      }
    }
    out.add(prev);
    return out;
  }
}