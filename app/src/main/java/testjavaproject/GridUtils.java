package testjavaproject;

import java.util.List;
import java.util.stream.Collectors;

public class GridUtils {

  // Pad all lines to the same width (right-pad with spaces)
  public static List<String> padRight(List<String> lines) {
    int width = lines.stream().mapToInt(String::length).max().orElse(0);
    return lines.stream()
        .map(s -> String.format("%-" + width + "s", s))
        .collect(Collectors.toList());
  }

  // Convert a list of equal-length strings into a char grid
  public static char[][] toCharGrid(List<String> lines) {
    if (lines.isEmpty())
      return new char[0][0];
    int rows = lines.size();
    int cols = lines.get(0).length();
    char[][] grid = new char[rows][cols];
    for (int i = 0; i < rows; i++) {
      grid[i] = lines.get(i).toCharArray();
    }
    return grid;
  }

  // Count 8-neighbour cells equal to target
  public static int countAdjacent(char[][] grid, int i, int j, char target) {
    int surrounds = 0;
    int[][] toCheck = {
        { i - 1, j },
        { i + 1, j },
        { i + 1, j - 1 },
        { i - 1, j - 1 },
        { i + 1, j + 1 },
        { i - 1, j + 1 },
        { i, j - 1 },
        { i, j + 1 },
    };
    for (var cells : toCheck) {
      int r = cells[0];
      int c = cells[1];
      if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length)
        continue;
      if (grid[r][c] == target)
        surrounds++;
    }
    return surrounds;
  }

  // Check whether the given column is all spaces
  public static boolean isSeparatorColumn(char[][] grid, int col) {
    for (int r = 0; r < grid.length; r++) {
      if (grid[r][col] != ' ')
        return false;
    }
    return true;
  }

  // Create a deep copy of the char grid
  public static char[][] copyGrid(char[][] grid) {
    char[][] out = new char[grid.length][];
    for (int i = 0; i < grid.length; i++)
      out[i] = grid[i].clone();
    return out;
  }

  // Iterate over every cell, providing row and column to the action
  public static void forEachCell(char[][] grid, java.util.function.BiConsumer<Integer, Integer> action) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        action.accept(i, j);
      }
    }
  }

  // Iterate over the 8 neighbors of (i,j) within bounds
  public static void forEachNeighbor(char[][] grid, int i, int j,
      java.util.function.BiConsumer<Integer, Integer> action) {
    for (int dr = -1; dr <= 1; dr++) {
      for (int dc = -1; dc <= 1; dc++) {
        if (dr == 0 && dc == 0)
          continue;
        int r = i + dr;
        int c = j + dc;
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length)
          continue;
        action.accept(r, c);
      }
    }
  }
}
