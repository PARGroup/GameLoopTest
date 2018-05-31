package event.handler;

import event.Event;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameEventHandler extends EventHandler {

  /**
   * 
   */
  public GameEventHandler() {
    super("Game Thread");
  }

  /**
   * @see event.handler.EventHandler#processEvent(event.Event)
   */
  @Override
  protected void processEvent(Event e) {
    System.out.println("Something would normally happen right now...");
  }

}
