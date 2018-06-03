package controller;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import event.ChipPlaceEvent;
import event.Event;
import event.StopEvent;
import javafx.scene.paint.Color;
import model.Board;
import model.Chip;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameController {

  private static final Queue<Event> EVENTS = new LinkedList<Event>();

  private static long prevTime;
  private static long accumulatedTime;

  private static long tickRate = TimeUnit.MILLISECONDS.toNanos(30);

  private static boolean running;
  private static boolean paused;

  private static Board board;
  

  private static boolean turn = true; //dont judge

  public static void startGame() {

    running = true;

    board = new Board();
    board.setChips(new Chip[Board.ROWS][Board.COLUMNS]);

    Thread gameThread = new Thread(new Runnable() {

      /**
       * @see java.lang.Runnable#run()
       */
      @Override
      public void run() {

        accumulatedTime = 0;
        prevTime = System.nanoTime();

        while (running) {

          long currentTime = System.nanoTime();
          long deltaTime = currentTime - prevTime;
          prevTime = currentTime;

          accumulatedTime += deltaTime;

          while (accumulatedTime >= tickRate) {
            accumulatedTime -= tickRate;
            GameController.tick();
          }

        }

      }

    }, "Game Thread");

    gameThread.start();

  }

  private synchronized static void tick() {

    while (!paused && !EVENTS.isEmpty()) {
      Event e = EVENTS.poll();
      processEvent(e);
    }

  }

  private static void processEvent(Event e) {

    if (e instanceof ChipPlaceEvent) {

      ChipPlaceEvent chipPlaceEvent = (ChipPlaceEvent) e;

      Chip chip = new Chip();
      if (turn) {
    	  chip.setColor(Color.BLUE);
      	  turn = false;
      } else {
    	  chip.setColor(Color.RED);
    	  turn = true;
      }

      placeChip(chip, chipPlaceEvent.getColumn());

    } else if (e instanceof StopEvent) {
      stopGame();
    }

  }

  private static void placeChip(Chip chip, int column) {

    Chip[][] chips = board.getChips();

    int row = 0;

    for (; row < chips.length && chips[row][column] == null; row++);

    if (row <= 0) {
      return;
    }

    row--;

    chips[row][column] = chip;
    UIController.chipPlaced(chip, column, row);

  }

  public synchronized static void addEvent(Event e) {
    EVENTS.add(e);
  }

  private static void stopGame() {
    running = false;
    UIController.terminate();
  }

  /**
   * @param paused the paused to set
   */
  public static void setPaused(boolean paused) {
    GameController.paused = paused;
  }

}
