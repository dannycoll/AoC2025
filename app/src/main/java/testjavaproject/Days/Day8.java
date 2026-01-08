package testjavaproject.Days;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import testjavaproject.Cable;
import testjavaproject.DSU;
import testjavaproject.GraphUtils;
import testjavaproject.Junction;

public class Day8 extends AbstractDay {

  private static final int PART1_CONNECTIONS = 1000;

  public static void main(String[] args) throws Exception {
    new Day8().run();
  }

  @Override
  protected int dayNumber() {
    return 8;
  }

  @Override
  protected void part1(List<String> input) throws Exception {
    var result = solvePart1(input, PART1_CONNECTIONS);
    System.out.println(result);
  }

  @Override
  protected void part2(List<String> input) throws Exception {
    var prepared = prepareCables(input);
    long lastXproduct = GraphUtils.lastXProductAfterKruskal(prepared.cables(), prepared.junctions());
    System.out.println(lastXproduct);
  }

  static long solvePart1(List<String> input, int pairsToProcess) {
    var prepared = prepareCables(input);
    DSU dsu = new DSU(prepared.junctions().size());

    // Make K actual connections (successful unions), skipping cables that connect
    // nodes already in the same component. This matches the problem description:
    // "connect together the K pairs which are closest together"
    int connectionsMade = 0;
    for (Cable cable : prepared.cables()) {
      if (dsu.union(cable.a, cable.b)) {
        connectionsMade++;
        if (connectionsMade == pairsToProcess) {
          break;
        }
      }
    }

    return productOfTopThreeSizes(dsu.componentSizes());
  }

  private static PreparedData prepareCables(List<String> input) {
    var junctions = input.stream().map(Junction::new).collect(Collectors.toList());
    List<Cable> cables = GraphUtils.buildCompleteEdges(junctions);
    cables.sort(Comparator.comparingLong(e -> e.distSq));
    return new PreparedData(junctions, cables);
  }

  private static long productOfTopThreeSizes(List<Integer> sizes) {
    // Ensure we have at least 3 sizes (pad with 1s if needed)
    while (sizes.size() < 3) {
      sizes.add(1);
    }
    return (long) sizes.get(0) * sizes.get(1) * sizes.get(2);
  }

  private static record PreparedData(List<Junction> junctions, List<Cable> cables) {
  }
}