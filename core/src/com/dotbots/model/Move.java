package com.dotbots.model;

public class Move {

  private Piece piece;
  private float x;
  private float y;

  // constructors
  // ----------------------------------------------------------------------------------------------

  public Move(Piece piece, float x, float y) {
    this.piece = piece;
    this.x = x;
    this.y = y;
  }

  public Move(Move move) {
    this.piece = new Piece(move.piece);
    this.x = move.getX();
    this.y = move.getY();
  }

  // getters & setters
  // ----------------------------------------------------------------------------------------------

  public Piece getPiece() { return piece; }
  public float getX() { return x; }
  public float getY() { return y; }
}
