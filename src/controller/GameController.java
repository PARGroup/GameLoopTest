package controller;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import event.ChipPlaceEvent;
import event.Event;
import event.StopEvent;

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

  public static void startGame() {

    running = true;

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

    while (!EVENTS.isEmpty()) {
      Event e = EVENTS.poll();
      processEvent(e);
    }

  }

  private static void processEvent(Event e) {

    if (e instanceof ChipPlaceEvent) {
      ChipPlaceEvent chipPlaceEvent = (ChipPlaceEvent) e;
      UIController.chipPlace(chipPlaceEvent.getColumn());
    } else if (e instanceof StopEvent) {
      stopGame();
    }

  }

  public synchronized static void addEvent(Event e) {
    EVENTS.add(e);
  }

  private static void stopGame() {
    running = false;
    UIController.terminate();
  }

}
