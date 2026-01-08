package testjavaproject;

public class Line {
  public long top, bottom, left, right;

  public Line(Tile t1, Tile t2) {
    this.top = Math.min(t1.x, t2.x);
    this.bottom = Math.max(t1.x, t2.x);
    this.left = Math.min(t1.y, t2.y);
    this.right = Math.max(t1.y, t2.y);
  }

  public long area() {
    return (Math.abs(this.top - this.bottom) + 1) * (Math.abs(this.left - this.right) + 1);
  }
}
