package controller;

import event.AnimationEvent;
import event.GameEvent;
import event.handler.AnimationEventHandler;
import event.handler.GameEventHandler;
import event.handler.GeneralEventHandler;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameController {

  private GeneralEventHandler eventHandler = new GeneralEventHandler();

  private GameEventHandler gameEventHandler = new GameEventHandler();
  private AnimationEventHandler animationEventHandler = new AnimationEventHandler();

  public void initialize() {

    eventHandler.addEventHandler(GameEvent.class, gameEventHandler);
    eventHandler.addEventHandler(AnimationEvent.class, animationEventHandler);

    gameEventHandler.start();
    animationEventHandler.start();

    eventHandler.start(false);

  }

  /**
   * 
   * @return The event handler which processes all incoming events.
   */
  public GeneralEventHandler getEventHandler() {
    return eventHandler;
  }

}
