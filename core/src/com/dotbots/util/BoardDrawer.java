package com.dotbots.util;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BoardDrawer {

  private ShapeRenderer renderer;
  private BoardViewTranslator bvt;

  // constructors
  // ---------------------------------------------------------------------------------------------

  public BoardDrawer(BoardViewTranslator bvt) {
    this.bvt = bvt;
    renderer = new ShapeRenderer();
  }

  // draw
  // ---------------------------------------------------------------------------------------------

  public void draw() {
    renderer.begin(ShapeRenderer.ShapeType.Filled);

    drawBackground();
    drawGoal();
    drawBorder();
    drawWalls();
    drawPieces();

    renderer.end();
  }

  private void drawBackground() {
    // draw board base
    renderer.setColor(1, 1, 1, 1);
    renderer.rect(bvt.boardOriginX, bvt.boardOriginY, bvt.boardWidth, bvt.boardWidth);

    // draw vertical lines
    renderer.setColor(.6f, .6f, .6f, 1);
    for (int i = 1; i < bvt.getSize(); i++) {
      float x = i * bvt.spotWidth - bvt.gridWidth / 2 + bvt.boardOriginX;
      renderer.rect(x, bvt.boardOriginY, bvt.gridWidth, bvt.boardWidth);
    }

    // draw horizontal lines
    for (int i = 1; i < bvt.getSize(); i++) {
      float y = i * bvt.spotWidth - bvt.gridWidth / 2 + bvt.boardOriginY;
      renderer.rect(bvt.boardOriginX, y, bvt.boardWidth, bvt.gridWidth);
    }
  }

  private void drawGoal() {
    renderer.setColor(bvt.getGoal().getColor());
    renderer.rect(bvt.getGoal().getX(), bvt.getGoal().getY(), bvt.spotWidth - bvt.gridWidth, bvt.spotWidth - bvt.gridWidth);
  }

  private void drawBorder() {
    renderer.setColor(.3f, .3f, .3f, 1);
    float l = bvt.boardWidth + bvt.wallWidth;
    renderer.rect(bvt.boardOriginX - bvt.wallWidth / 2, bvt.boardOriginY - bvt.wallWidth / 2, bvt.wallWidth, l);
    renderer.rect(bvt.boardOriginX - bvt.wallWidth / 2, bvt.boardOriginY - bvt.wallWidth / 2, l, bvt.wallWidth);
    renderer.rect(bvt.boardOriginX + bvt.boardWidth - bvt.wallWidth / 2, bvt.boardOriginY - bvt.wallWidth / 2, bvt.wallWidth, l);
    renderer.rect(bvt.boardOriginX - bvt.wallWidth / 2, bvt.boardOriginY + bvt.boardWidth - bvt.wallWidth / 2, l, bvt.wallWidth);

  }

  private void drawPieces() {
    for (com.dotbots.model.Piece piece : bvt.getPieces()) {
      renderer.setColor(piece.getColor());
      renderer.circle(piece.getX(), piece.getY(), bvt.pieceRadius);
    }
  }

  private void drawWalls() {
    renderer.setColor(.3f, .3f, .3f, 1);
    for (com.dotbots.model.Wall wall : bvt.getWalls()) {
      switch (wall.getDir()) {
        case 0:
          drawWallSegment(wall.getX(), wall.getY(), true);
          drawWallSegment(wall.getX(), wall.getY(), false);
          break;
        case 1:
          drawWallSegment(wall.getX(), wall.getY() - bvt.spotWidth, true);
          drawWallSegment(wall.getX(), wall.getY(), false);
          break;
        case 2:
          drawWallSegment(wall.getX(), wall.getY() - bvt.spotWidth, true);
          drawWallSegment(wall.getX() - bvt.spotWidth, wall.getY(), false);
          break;
        case 3:
          drawWallSegment(wall.getX(), wall.getY(), true);
          drawWallSegment(wall.getX() - bvt.spotWidth, wall.getY(), false);
          break;
      }
    }
  }

  // if vertical is true, draw vertical line, else horizontal line, each with origin at (x1, y1)
  private void drawWallSegment(float x, float y, boolean vertical) {
    if (vertical) {
      y += bvt.gridWidth;
      renderer.rect(x, y, bvt.wallWidth, bvt.wallLength);
    } else {
      x += bvt.gridWidth;
      renderer.rect(x, y, bvt.wallLength, bvt.wallWidth);
    }
  }
}
