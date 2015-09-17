package com.dotbots.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class Button {

  private String label;
  private float x;
  private float y;
  private float width;
  private float height;
  private Color color;
  private Texture icon;
  private boolean visible;

  // constructors
  // ----------------------------------------------------------------------------------------------

  public Button(String label, float x, float y, float width, float height, Color color,
                String src, boolean visible) {
    this.label = label;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.color = color;
    this.icon = new Texture(Gdx.files.internal(src));
    this.visible = visible;
  }

  // getters & setters
  // ----------------------------------------------------------------------------------------------

  public String getLabel() { return label; }
  public float getX() { return x; }
  public float getY() { return y; }
  public float getWidth() { return width; }
  public float getHeight() { return height; }
  public Color getColor() { return color; }
  public Texture getIcon() { return icon; }
  public boolean isVisible() { return visible; }

  public void setVisible(boolean visible) { this.visible = visible; }
}
