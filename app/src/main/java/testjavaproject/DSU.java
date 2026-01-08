package testjavaproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DSU {
  int[] parent;
  int[] size;

  public DSU(int n) {
    parent = new int[n];
    size = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
      size[i] = 1;
    }
  }

  public int find(int a) {
    while (parent[a] != a) {
      parent[a] = parent[parent[a]];
      a = parent[a];
    }
    return a;
  }

  public boolean union(int a, int b) {
    int ra = find(a);
    int rb = find(b);
    if (ra == rb)
      return false;
    if (size[ra] < size[rb]) {
      int tmp = ra;
      ra = rb;
      rb = tmp;
    }
    parent[rb] = ra;
    size[ra] += size[rb];
    return true;
  }

  public List<Integer> componentSizes() {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < parent.length; i++) {
      int r = find(i);
      map.put(r, map.getOrDefault(r, 0) + 1);
    }
    List<Integer> sizes = new ArrayList<>(map.values());
    sizes.sort(Collections.reverseOrder());
    return sizes;
  }
}
