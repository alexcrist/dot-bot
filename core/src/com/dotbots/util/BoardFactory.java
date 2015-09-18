package com.dotbots.util;

import com.badlogic.gdx.graphics.Color;
import com.dotbots.model.Board;
import com.dotbots.model.Goal;
import com.dotbots.model.Piece;
import com.dotbots.model.Wall;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class BoardFactory {

  // static board creation
  // ----------------------------------------------------------------------------------------------

  public static Board createBoard(int size) {
    List<Piece> pieces = createPieces(size);
    List<Wall> walls = createWalls(size);
    Goal goal = createGoal(size, pieces, walls);
    return new Board(size, pieces, walls, goal);
  }

  // methods to create pieces, walls, and goal
  // ----------------------------------------------------------------------------------------------

  private static List<Piece> createPieces(int size) {
    int[] keys = {};
    switch (size) {
      case 10:
        keys = pieceKeys10;
        break;
    }
    List<Piece> pieces = new ArrayList<Piece>();
    for (int i = 0; i < keys.length; i += 2) {
      Piece piece = new Piece(keys[i], keys[i + 1], pieceColors[i / 2]);
      pieces.add(piece);
    }
    return pieces;
  }

  private static List<Wall> createWalls(int size) {
    int[] keys = {};
    switch (size) {
      case 10:
        keys = wallKeys10;
        break;
    }
    List<Wall> walls = new ArrayList<Wall>();
    for (int i = 0; i < keys.length; i += 3) {
      Wall wall = new Wall(keys[i], keys[i + 1], keys[i + 2]);
      walls.add(wall);
    }
    return walls;
  }

  // TODO - this whole method is sloppy, should be optimized
  public static Goal createGoal(int size, List<Piece> pieces, List<Wall> walls) {
    Random rand = new Random();
    int randNum = rand.nextInt(pieces.size());
    Piece piece = pieces.get(randNum);

    List<Goal> goals = new ArrayList<Goal>();
    for (Wall wall : walls) {
      float x = wall.getX();
      float y = wall.getY();
      Goal goal0 = new Goal(x, y - 1, piece);
      Goal goal1 = new Goal(x, y, piece);
      Goal goal2 = new Goal(x - 1, y, piece);
      Goal goal3 = new Goal(x - 1, y - 1, piece);
      goals.add(goal0);
      goals.add(goal1);
      goals.add(goal2);
      goals.add(goal3);
      switch (wall.getDir()) {
        case 0: goals.remove(goal3); break;
        case 1: goals.remove(goal2); break;
        case 2: goals.remove(goal1); break;
        case 3: goals.remove(goal0); break;
      }
    }
    List<Goal> potentialGoals = new ArrayList<Goal>();
    for (Goal goal : goals) {
      boolean spawn = true;
      for (Piece each : pieces) {
        if (each.getX() == goal.getX() && each.getY() == goal.getY()) {
          spawn = false;
        }
      }
      if (spawn && !(goal.getX() < 0 || goal.getX() > size - 1 || goal.getY() < 0
          || goal.getY() > size - 1)) {
        potentialGoals.add(goal);
      }
    }
    randNum = rand.nextInt(potentialGoals.size());
    return potentialGoals.get(randNum);
  }

  // colors for pieces
  // ----------------------------------------------------------------------------------------------

  final static Color[] pieceColors = {
      Color.valueOf("FFAA00"),
      Color.valueOf("FF00AA"),
      Color.valueOf("AA00FF"),
      Color.valueOf("00AAFF")
  };

  // keys for pieces
  // ----------------------------------------------------------------------------------------------

  final static int[] pieceKeys10 = {
      2, 7, // x, y
      3, 4,
      6, 2,
      7, 7 };


  // keys for walls
  // ----------------------------------------------------------------------------------------------

  final static int[] wallKeys10 = {
      0, 2, 0, // x, y, dir
      0, 7, 0,
      2, 7, 0,
      3, 4, 0,
      3, 10, 1,
      4, 0, 0,
      6, 6, 2,
      7, 0, 0,
      7, 3, 2,
      7, 10, 1,
      8, 8, 2,
      10, 2, 2,
      10, 6, 2 };
}
