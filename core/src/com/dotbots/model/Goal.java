package com.dotbots.model;

import com.badlogic.gdx.graphics.Color;

public class Goal {

  private float x;
  private float y;
  private Color color;
  private Piece piece;

  // constructors
  // ----------------------------------------------------------------------------------------------

  public Goal(float x, float y, Color color, Piece piece) {
    this.x = x;
    this.y = y;
    this.color = color;
    this.piece = piece;
  }

  public Goal(Goal goal) {
    this.x = goal.getX();
    this.y = goal.getY();
    this.color = goal.getColor();
    this.piece = goal.getPiece(); // it is intentional that we are not creating a new piece object
  }

  // getters & setters
  // ----------------------------------------------------------------------------------------------

  public float getX() { return x; }
  public float getY() { return y; }
  public Color getColor() { return color; }
  public Piece getPiece() { return piece; }

  public void setX(float x) { this.x = x; }
  public void setY(float y) { this.y = y; }
}
