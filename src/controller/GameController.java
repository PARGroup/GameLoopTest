package controller;

import java.util.concurrent.TimeUnit;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameController {

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

  private static void tick() {

  }

  public static void stopGame() {
    running = false;
  }

}
