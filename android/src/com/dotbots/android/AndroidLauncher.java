package com.dotbots.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.dotbots.DotBotsGame;

public class AndroidLauncher extends AndroidApplication {
  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
    config.useAccelerometer = false;
    config.useCompass = false;

    initialize(new DotBotsGame(), config);
  }
}
