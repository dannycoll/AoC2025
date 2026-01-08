package testjavaproject.Days;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

import testjavaproject.Line;
import testjavaproject.Tile;

public class Day9 extends AbstractDay {

  public static void main(String[] args) throws Exception {
    new Day9().run();
  }

  @Override
  protected int dayNumber() {
    return 9;
  }

  @Override
  protected void part1(List<String> input) throws Exception {
    List<Tile> tiles = parseTiles(input);
    solve(tiles, (a, b) -> true);
  }

  @Override
  protected void part2(List<String> input) throws Exception {
    List<Tile> tiles = parseTiles(input);
    var perim = createPerim(tiles);
    System.out.println(perim.size());
    long max = findMaxValidArea(tiles, perim);
    System.out.println(max);
  }

  private static List<Tile> parseTiles(List<String> input) {
    return input.stream().map(Tile::new).toList();
  }

  private static long findMaxValidArea(List<Tile> tiles, List<Line> perim) {
    return IntStream.range(0, tiles.size())
        .boxed()
        .flatMap(i -> IntStream.range(i + 1, tiles.size())
            .mapToObj(j -> new Line(tiles.get(i), tiles.get(j))))
        .filter(rect -> !isInvalid(rect, perim))
        .mapToLong(Line::area)
        .max()
        .orElse(0);
  }

  private static boolean isInvalid(Line toCheck, List<Line> perim) {
    return perim.stream()
        .anyMatch(line -> line.top < toCheck.bottom && line.bottom > toCheck.top &&
            line.left < toCheck.right && line.right > toCheck.left);
  }

  private static List<Line> createPerim(List<Tile> tiles) {
    List<Line> lines = IntStream.range(1, tiles.size())
        .mapToObj(i -> new Line(tiles.get(i - 1), tiles.get(i)))
        .collect(java.util.stream.Collectors.toCollection(java.util.ArrayList::new));
    // Close the loop: connect last tile back to first tile
    lines.add(new Line(tiles.get(tiles.size() - 1), tiles.get(0)));
    return lines;
  }

  private static void solve(List<Tile> input, BiPredicate<Tile, Tile> isValid) {
    long max = IntStream.range(0, input.size() - 1)
        .boxed()
        .flatMap(i -> IntStream.range(i + 1, input.size())
            .mapToObj(j -> new TilePair(input.get(i), input.get(j))))
        .filter(pair -> isValid.test(pair.a(), pair.b()))
        .mapToLong(pair -> (long) (Math.abs(pair.a().x - pair.b().x) + 1)
            * (Math.abs(pair.a().y - pair.b().y) + 1))
        .max()
        .orElse(0);
    System.out.println(max);
  }

  private static record TilePair(Tile a, Tile b) {
  }
}
