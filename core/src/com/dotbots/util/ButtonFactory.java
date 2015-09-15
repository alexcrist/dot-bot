package com.dotbots.util;

import com.badlogic.gdx.graphics.Color;
import com.dotbots.model.Button;

public class ButtonFactory {

  public static Button createUndo(BoardViewTranslator bvt) {
    float x = bvt.boardOriginX;
    float y = x;
    float w = bvt.boardWidth / 2 - x;
    float h = bvt.boardOriginY - 2 * x;
    Color color = Color.valueOf("999999");
    return new Button("undo", x, y, w, h, color, "undo.png");
  }

  public static Button createReset(BoardViewTranslator bvt) {
    float x = bvt.boardWidth / 2 + 1.5f * bvt.boardOriginX;
    float y = bvt.boardOriginX;
    float w = bvt.boardWidth / 2 - bvt.boardOriginX;
    float h = bvt.boardOriginY - 2 * bvt.boardOriginX;
    Color color = Color.valueOf("999999");
    return new Button("reset", x, y, w, h, color, "reset.png");
  }
}
