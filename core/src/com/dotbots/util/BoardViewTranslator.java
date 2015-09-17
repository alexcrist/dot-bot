package com.dotbots.util;

import com.dotbots.model.Board;
import com.dotbots.model.Goal;
import com.dotbots.model.Piece;
import com.dotbots.model.Wall;

import java.util.List;

public class BoardViewTranslator {

  private Board board;
  // This board (translatedBoard) is a copy of the board given in the constructor BUT instead of
  //   having nice integer values for the piece/wall/etc. position values, they are in exact pixels.
  private Board translatedBoard;

  public final float boardWidth;
  public final float boardOriginX;
  public final float boardOriginY;

  public final float spotWidth;
  public final float pieceRadius;
  public final float gridWidth;
  public final float wallWidth;
  public final float wallLength;

  // constructors
  // ----------------------------------------------------------------------------------------------

  public BoardViewTranslator(Board board, float screenWidth, float screenHeight) {
    this.board = board;
    init();

    boardWidth = screenWidth * .9f;
    boardOriginX = (screenWidth - boardWidth) / 2;
    boardOriginY = (screenHeight - boardWidth) / 2;

    spotWidth = boardWidth / board.getSize();
    pieceRadius = spotWidth * .4f;

    gridWidth = screenWidth / 270;
    wallWidth = gridWidth * 3;
    wallLength = spotWidth + gridWidth;

    translate();
  }

  // initialize translated board
  // ----------------------------------------------------------------------------------------------

  // this method is used when one of Board's objects is recreated such as a new goal being created
  public void init() {
    translatedBoard = new Board(board);
  }

  // translate all the things
  // ----------------------------------------------------------------------------------------------

  public void translate() {
    for (int i = 0; i < board.getPieces().size(); i++) {
      translatedBoard.getPieces().get(i).setX(translatePieceX(board.getPieces().get(i)));
      translatedBoard.getPieces().get(i).setY(translatePieceY(board.getPieces().get(i)));
    }
    for (int i = 0; i < board.getWalls().size(); i++) {
      translatedBoard.getWalls().get(i).setX(translateWallX(board.getWalls().get(i)));
      translatedBoard.getWalls().get(i).setY(translateWallY(board.getWalls().get(i)));
    }
    translatedBoard.getGoal().setX(translatedGoalX(board.getGoal()));
    translatedBoard.getGoal().setY(translatedGoalY(board.getGoal()));
  }

  private float translatePieceX(Piece piece) {
    return boardOriginX + (piece.getX() + .5f) * spotWidth;
  }

  private float translatePieceY(Piece piece) {
    return boardOriginY + (piece.getY() + .5f) * spotWidth;
  }

  private float translateWallX(Wall wall) {
    return boardOriginX + wall.getX()* spotWidth - wallWidth / 2;
  }

  private float translateWallY(Wall wall) {
    return boardOriginY + wall.getY() * spotWidth - wallWidth / 2;
  }

  private float translatedGoalX(Goal goal) {
    return boardOriginX + goal.getX() * spotWidth + gridWidth / 2;
  }

  private float translatedGoalY(Goal goal) {
    return boardOriginY + goal.getY() * spotWidth + gridWidth / 2;
  }

  // getters & setters
  // ---------------------------------------------------------------------------------------------

  public int getSize() { return board.getSize(); }
  public List<Piece> getPieces() { return translatedBoard.getPieces(); }
  public List<Wall> getWalls() { return translatedBoard.getWalls(); }
  public Goal getGoal() { return translatedBoard.getGoal(); }
}
