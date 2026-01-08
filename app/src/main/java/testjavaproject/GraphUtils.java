package testjavaproject;

import java.util.ArrayList;
import java.util.List;

public class GraphUtils {

  // Build complete graph edges (all pairs) from junction list
  public static List<Cable> buildCompleteEdges(List<Junction> junctions) {
    List<Cable> cables = new ArrayList<>();
    for (int i = 0; i < junctions.size(); i++) {
      var a = junctions.get(i);
      for (int j = i + 1; j < junctions.size(); j++) {
        var b = junctions.get(j);

        long dx = a.x - b.x;
        long dy = a.y - b.y;
        long dz = a.z - b.z;
        long dist2 = dx * dx + dy * dy + dz * dz;
        cables.add(new Cable(dist2, i, j));
      }
    }
    return cables;
  }

  // Apply the first K edges (by order in the list) as unions on the DSU
  public static void applyFirstKUnions(List<Cable> cables, DSU dsu, int k) {
    for (int i = 0; i < Math.min(k, cables.size()); i++) {
      Cable e = cables.get(i);
      dsu.union(e.a, e.b);
    }
  }

  // Run Kruskal-like unions until all nodes are connected, returning the product
  // of x coordinates of the last edge's endpoints (as Day8 computed)
  public static long lastXProductAfterKruskal(List<Cable> cables, List<Junction> junctions) {
    int n = junctions.size();
    DSU dsu2 = new DSU(n);
    int merges = 0;
    long lastXproduct = -1;
    for (Cable e : cables) {
      if (dsu2.union(e.a, e.b)) {
        merges++;
        if (merges == n - 1) {
          long xa = junctions.get(e.a).x;
          long xb = junctions.get(e.b).x;
          lastXproduct = xa * xb;
          break;
        }
      }
    }
    return lastXproduct;
  }
}
