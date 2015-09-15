package com.dotbots.model;

public class Wall {

  private float x;
  private float y;

  /* Each wall is a corner, so really two walls. There are four possible orientations, each denoted
       by a integer. 0 = opens up and right like an "L". 1 = opens down and right. 2 = down and
       left. 3 = up and left. */
  private int dir;

  public Wall(float x, float y, int dir) {
    this.x = x;
    this.y = y;
    this.dir = dir;
  }

  public Wall(Wall wall) {
    x = wall.getX();
    y = wall.getY();
    dir = wall.getDir();
  }

  public float getX() { return x; }
  public float getY() { return y; }
  public int getDir() { return dir; }

  public void setX(float x) { this.x = x; }
  public void setY(float y) { this.y = y; }
}
