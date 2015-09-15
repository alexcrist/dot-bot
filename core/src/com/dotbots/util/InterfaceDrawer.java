package com.dotbots.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.dotbots.model.Button;

import java.util.List;

public class InterfaceDrawer {

  private List<Button> buttons;
  private SpriteBatch batch;
  private ShapeRenderer renderer;

  // constructors
  // ---------------------------------------------------------------------------------------------

  public InterfaceDrawer(List<Button> buttons) {
    this.buttons = buttons;
    batch = new SpriteBatch();
    renderer = new ShapeRenderer();
  }

  // draw
  // ---------------------------------------------------------------------------------------------

  public void draw() {
    renderer.begin(ShapeRenderer.ShapeType.Filled);
    drawButtonBgs();
    renderer.end();

    batch.begin();
    drawButtonIcons();
    batch.end();
  }

  private void drawButtonBgs() {
    for (Button button : buttons) {
      renderer.setColor(button.getColor());
      renderer.rect(button.getX(), button.getY(), button.getWidth(), button.getHeight());
    }
  }

  private void drawButtonIcons() {
    for (Button button : buttons) {
      Texture icon = button.getIcon();
      float centerX = button.getX() + button.getWidth() / 2 - icon.getWidth() / 2;
      float centerY = button.getY() + button.getHeight() / 2 - icon.getHeight() / 2;
      batch.draw(icon, centerX, centerY);
    }
  }
}
