package testjavaproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tile {
  public long x, y;

  public Tile(String coords) {
    var parts = coords.split(",");
    this.x = Long.parseLong(parts[0]);
    this.y = Long.parseLong(parts[1]);
  }

  public Tile(long x, long y) {
    this.x = x;
    this.y = y;
  }

}
