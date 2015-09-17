package com.dotbots;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.dotbots.model.Board;
import com.dotbots.model.Button;
import com.dotbots.util.BoardDrawer;
import com.dotbots.util.BoardFactory;
import com.dotbots.util.BoardViewTranslator;
import com.dotbots.util.ButtonFactory;
import com.dotbots.util.InputHandler;
import com.dotbots.util.InterfaceDrawer;

import java.util.ArrayList;
import java.util.List;

public class DotBotsGame extends ApplicationAdapter {

  private Board board;
  private BoardViewTranslator bvt;
  private List<Button> buttons;
  private BoardDrawer boardDrawer;
  private InterfaceDrawer interfaceDrawer;

  @Override
  public void create() {
    // get screen width and height
    int w = Gdx.graphics.getWidth();
    int h = Gdx.graphics.getHeight();

    // new board and its board view translator
    board = BoardFactory.createBoard(10);
    bvt = new BoardViewTranslator(board, w, h);

    // new buttons
    buttons = new ArrayList<Button>();
    buttons.add(ButtonFactory.createReset(bvt));
    buttons.add(ButtonFactory.createUndo(bvt));
    buttons.add(ButtonFactory.createNext(bvt));

    // new drawers
    boardDrawer = new BoardDrawer(bvt);
    interfaceDrawer = new InterfaceDrawer(board, bvt, buttons);

    // handle input
    Gdx.input.setInputProcessor(new InputHandler(board, bvt, buttons, h));
  }

  @Override
  public void render() {
    // set up the screen
    Gdx.gl.glClearColor(.9f, .9f, .9f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // draw all the things
    board.update();
    bvt.translate();
    boardDrawer.draw();
    interfaceDrawer.draw();
  }
}
