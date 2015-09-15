package com.dotbots.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.dotbots.DotBotsGame;

public class HtmlLauncher extends GwtApplication {

  @Override
  public GwtApplicationConfiguration getConfig () {
    return new GwtApplicationConfiguration(600, 800);
  }

  @Override
  public ApplicationListener getApplicationListener () {
    return new DotBotsGame();
  }
}