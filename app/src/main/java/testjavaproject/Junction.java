package testjavaproject;

public class Junction {
  public int x, y, z;

  public Junction(String coords) {
    var parts = coords.split(",");
    this.x = Integer.parseInt(parts[0]);
    this.y = Integer.parseInt(parts[1]);
    this.z = Integer.parseInt(parts[2]);
  }

  public Junction(String[] xyCoordStrings) {
    this.x = Integer.parseInt(xyCoordStrings[0]);
    this.y = Integer.parseInt(xyCoordStrings[1]);
  }
}
