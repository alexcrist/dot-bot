package com.dotbots.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.dotbots.model.Board;
import com.dotbots.model.Button;

import java.util.List;

public class InterfaceDrawer {

  private Board board;
  private BoardViewTranslator bvt;
  private List<Button> buttons;
  private SpriteBatch batch;
  private ShapeRenderer renderer;
  private BitmapFont font;

  // constructors
  // ---------------------------------------------------------------------------------------------

  public InterfaceDrawer(Board board, BoardViewTranslator bvt, List<Button> buttons) {
    this.board = board;
    this.bvt = bvt;
    this.buttons = buttons;
    batch = new SpriteBatch();
    renderer = new ShapeRenderer();
    font = new BitmapFont(Gdx.files.internal("auto.fnt"), Gdx.files.internal("auto.png"), false);
  }

  // draw
  // ---------------------------------------------------------------------------------------------

  public void draw() {
    buttons.get(2).setVisible(board.getSuccess()); // buttons.get(2) is the next button

    renderer.begin(ShapeRenderer.ShapeType.Filled);
    drawButtonBgs();
    renderer.end();

    batch.begin();
    drawButtonIcons();
    drawMoveCount();
    batch.end();
  }

  private void drawButtonBgs() {
    for (Button button : buttons) {
      if(button.isVisible()) {
        renderer.setColor(button.getColor());
        renderer.rect(button.getX(), button.getY(), button.getWidth(), button.getHeight());
      }
    }
  }

  private void drawButtonIcons() {
    for (Button button : buttons) {
      if (button.isVisible()) {
        Texture icon = button.getIcon();
        float centerX = button.getX() + button.getWidth() / 2 - icon.getWidth() / 2;
        float centerY = button.getY() + button.getHeight() / 2 - icon.getHeight() / 2;
        batch.draw(icon, centerX, centerY);
      }
    }
  }

  private void drawMoveCount() {
    float x = bvt.boardOriginX;
    float y = bvt.boardOriginY + bvt.boardWidth + bvt.boardOriginX;
    font.draw(batch, Integer.toString(board.getMoves().size()), x, y);
  }
}
