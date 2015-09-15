package com.dotbots.model;

import com.badlogic.gdx.graphics.Color;

public class Piece {

  private float x;
  private float y;
  private Color color;
  private float destX;
  private float destY;

  public Piece(float x, float y, Color color) {
    this.x = x;
    this.y = y;
    this.color = color;
    this.destX = x;
    this.destY = y;
  }

  public Piece(Piece piece) {
    this.x = piece.getX();
    this.y = piece.getY();
    this.color = piece.getColor();
  }

  public void update() {
    float speed = .3f;
    if (x < destX) {
      x = x + speed > destX ? destX : x + speed;
    } else if (x > destX) {
      x = x - speed < destX ? destX : x - speed;
    }
    if (y < destY) {
      y = y + speed > destY ? destY : y + speed;
    } else if (y > destY) {
      y = y - speed < destY ? destY : y - speed;
    }
  }

  public float getX() { return x; }
  public float getY() { return y; }
  public Color getColor() { return color; }
  public float getDestX() { return destX; }
  public float getDestY() { return destY; }
  public boolean isStill() { return x == destX && y == destY; }

  public void setX(float x) { this.x = x; }
  public void setY(float y) { this.y = y; }
  public void setDestX(float destX) { this.destX = destX; }
  public void setDestY(float destY) { this.destY = destY; }
}
