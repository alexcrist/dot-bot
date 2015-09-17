package com.dotbots.util;

import com.badlogic.gdx.InputProcessor;
import com.dotbots.model.Board;
import com.dotbots.model.Button;
import com.dotbots.model.Piece;

import java.util.List;

public class InputHandler implements InputProcessor {

  private Board board;
  private BoardViewTranslator bvt;
  private List<Button> buttons;
  private int screenHeight;

  private boolean touched;
  private Piece touchedPiece;
  private float touchX;
  private float touchY;

  // constructors
  // ----------------------------------------------------------------------------------------------

  public InputHandler(Board board, BoardViewTranslator bvt, List<Button> buttons, int screenHeight) {
    this.board = board;
    this.bvt = bvt;
    this.buttons = buttons;
    this.screenHeight = screenHeight;
    touched = false;
  }

  // get touch input
  // ----------------------------------------------------------------------------------------------

  // An important thing to note is the difference in coordinate systems between the drawer and the
  //   input processor: The drawer (and bvt) say y = 0 is the bottom of the screen. The
  //   InputProcessor claims y = 0 is the top.
  @Override
  public boolean touchDown(int x, int y, int pointer, int button) {
    y = screenHeight - y;

    checkPieceTouched(x, y);
    checkButtonTouched(x, y);

    return true;
  }

  @Override
  public boolean touchDragged(int x, int y, int pointer) {
    y = screenHeight - y;

    checkPieceDragged(x, y);

    return true;
  }

  @Override
  public boolean touchUp(int x, int y, int pointer, int button) {
    touched = false;
    return true;
  }

  // check what was touched
  // ----------------------------------------------------------------------------------------------

  private void checkPieceTouched(int x, int y) {
    for (int i = 0; i < bvt.getPieces().size(); i++) {
      Piece piece = board.getPieces().get(i);
      Piece bvtPiece = bvt.getPieces().get(i);
      float dx = x - bvtPiece.getX();
      float dy = y - bvtPiece.getY();
      if (Math.sqrt(dx * dx + dy * dy) < bvt.spotWidth / 2 && piece.isStill()) {
        touchX = x;
        touchY = y;
        touched = true;
        touchedPiece = piece;
      }
    }
  }

  private void checkButtonTouched(int x, int y) {
    for (Button button : buttons) {
      // The reason we nest the ifs is so that if any condition fails, it doesn't even check the
      //   others - hence a small performance increase.
      if (button.isVisible()) {
        if (x >= button.getX()) {
          if (x <= button.getX() + button.getWidth()) {
            if (y >= button.getY()) {
              if (y <= button.getY() + button.getHeight()) {
                if (button.getLabel().equals("undo")) {
                  board.undo();
                } else if (button.getLabel().equals("reset")) {
                  board.reset();
                } else if (button.getLabel().equals("next")) {
                  board.next();
                  bvt.init(); // necessary to reinitialize goal
                }
              }
            }
          }
        }
      }
    }
  }

  // drag checks
  // ----------------------------------------------------------------------------------------------

  private void checkPieceDragged(int x, int y) {
    if (touched) {
      float dx = x - touchX;
      float dy = y - touchY;
      if (Math.sqrt(dx * dx + dy * dy) > bvt.spotWidth) {
        int dir;
        if (Math.abs(dy) > Math.abs(dx)) {
          dir = dy > 0 ? 0 : 2;
        } else {
          dir = dx > 0 ? 1 : 3;
        }
        touched = false;
        board.makeMove(touchedPiece, dir);
      }
    }
  }

  // unused touch inputs
  // ----------------------------------------------------------------------------------------------

  @Override public boolean keyDown(int keycode) { return false; }
  @Override public boolean keyUp(int keycode) { return false; }
  @Override public boolean keyTyped(char character) { return false; }
  @Override public boolean mouseMoved(int screenX, int screenY) { return false; }
  @Override public boolean scrolled(int amount) { return false; }
}
