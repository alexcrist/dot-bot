package com.dotbots.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

  private int size;
  private List<Piece> pieces;
  private List<Wall> walls;
  private Goal goal;
  private List<Move> moves;

  private boolean resetting;

  // constructors
  // ----------------------------------------------------------------------------------------------

  public Board(int size, List<Piece> pieces, List<Wall> walls, Goal goal) {
    this.size = size;
    this.pieces = pieces;
    this.walls = walls;
    this.goal = goal;
    this.moves = new ArrayList<Move>();
    this.resetting = false;
  }

  public Board(Board board) {
    this.pieces = new ArrayList<Piece>();
    this.walls = new ArrayList<Wall>();
    this.goal = new Goal(board.getGoal());
    this.size = board.getSize();
    for (Piece piece : board.getPieces()) { this.pieces.add(new Piece(piece)); }
    for (Wall wall : board.getWalls()) { this.walls.add(new Wall(wall)); }
    for (Move move : board.getDirs()) { this.moves.add(new Move(move));  }
    this.resetting = board.resetting;
  }

  // update
  // ---------------------------------------------------------------------------------------------

  public void update() {
    for (Piece piece : pieces) {
      piece.update();
    }
    if (resetting) {
      resetUpdate();
    }
    checkSuccess();
  }

  // make move
  // ---------------------------------------------------------------------------------------------

  public void makeMove(Piece piece, int dir) {
    float x = piece.getX();
    float y = piece.getY();
    while (canMove(x, y, dir)) {
      switch (dir) {
        case 0: y++; break;
        case 1: x++; break;
        case 2: y--; break;
        case 3: x--; break;
      }
    }
    if (piece.getX() != x || piece.getY() != y) {
      moves.add(new Move(piece, piece.getX(), piece.getY()));
      piece.setDestX(x);
      piece.setDestY(y);
    }
  }

  // TODO - hopefully this can be optimized
  private boolean canMove(float x, float y, int dir) {
    switch (dir) {
      case 0:
        if (y == size - 1) {
          return false;
        }
        for (Piece piece : pieces) {
          if (piece.getY() == y + 1 && piece.getX() == x) {
            return false;
          }
        }
        for (Wall wall : walls) {
          if (wall.getY() == y + 1 && ((wall.getX() == x && (wall.getDir() == 0
              || wall.getDir() == 1)) || (wall.getX() == x + 1 && (wall.getDir() == 2
              || wall.getDir() == 3)))) {
            return false;
          }
        }
        break;
      case 1:
        if (x == size - 1) {
          return false;
        }
        for (Piece piece : pieces) {
          if (piece.getX() == x + 1 && piece.getY() == y) {
            return false;
          }
        }
        for (Wall wall : walls) {
          if (wall.getX() == x + 1 && ((wall.getY() == y && (wall.getDir() == 0
              || wall.getDir() == 3)) || (wall.getY() == y + 1 && (wall.getDir() == 1
              || wall.getDir() == 2)))) {
            return false;
          }
        }
        break;
      case 2:
        if (y == 0) {
          return false;
        }
        for (Piece piece : pieces) {
          if (piece.getY() == y - 1 && piece.getX() == x) {
            return false;
          }
        }
        for (Wall wall : walls) {
          if (wall.getY() == y && ((wall.getX() == x && (wall.getDir() == 0
              || wall.getDir() == 1)) || (wall.getX() == x + 1 && (wall.getDir() == 2
              || wall.getDir() == 3)))) {
            return false;
          }
        }
        break;
      case 3:
        if (x == 0) {
          return false;
        }
        for (Piece piece : pieces) {
          if (piece.getX() == x - 1 && piece.getY() == y) {
            return false;
          }
        }
        for (Wall wall : walls) {
          if (wall.getX() == x && ((wall.getY() == y && (wall.getDir() == 0
              || wall.getDir() == 3)) || (wall.getY() == y + 1 && (wall.getDir() == 1
              || wall.getDir() == 2)))) {
            return false;
          }
        }
        break;
    }
    return true;
  }

  // undo move
  // ---------------------------------------------------------------------------------------------

  public void undoMove() {
    if (moves.size() > 0) {
      Move lastMove = moves.get(moves.size() - 1);
      Piece piece = lastMove.getPiece();
      if (piece.isStill()) {
        piece.setDestX(lastMove.getX());
        piece.setDestY(lastMove.getY());
        moves.remove(lastMove);
      }
    }
  }

  // reset all moves
  // ---------------------------------------------------------------------------------------------

  public void reset() {
    resetting = true;
  }

  private void resetUpdate() {
    if (moves.size() > 0) {
      undoMove();
    } else {
      resetting = false;
    }
  }

  // checks if piece made it to the goal
  // ----------------------------------------------------------------------------------------------

  private void checkSuccess() {
    Piece piece = goal.getPiece();
    if (goal.getX() == piece.getX() && goal.getY() == piece.getY()) {
      // TODO - fun animation
      // TODO - display continue button
    }
  }

  // getters & setters
  // ----------------------------------------------------------------------------------------------

  public List<Piece> getPieces() { return pieces; }
  public List<Wall> getWalls() { return walls; }
  public Goal getGoal() { return goal; }
  public List<Move> getDirs() { return moves; }
  public int getSize() { return size; }
}
