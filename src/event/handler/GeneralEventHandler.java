package event.handler;

import java.util.HashMap;
import java.util.LinkedList;
import event.Event;
import event.StopGameEvent;

/**
 * @author Rawad Aboudlal
 *
 */
public class GeneralEventHandler extends EventHandler {

  private final HashMap<Class<?>, LinkedList<EventHandler>> handlers =
      new HashMap<Class<?>, LinkedList<EventHandler>>();

  /**
   * 
   */
  public GeneralEventHandler() {
    super("Event Thread");
  }

  /**
   * @see event.handler.EventHandler#processEvent(event.Event)
   */
  @Override
  protected void processEvent(Event e) {

    if (e instanceof StopGameEvent) {
      stop();

      for (Class<?> eventType : handlers.keySet()) {
        LinkedList<EventHandler> currentHandlers = handlers.get(eventType);
        for (EventHandler handler : currentHandlers) {
          handler.stop();
        }
      }

      return;
    }

    LinkedList<EventHandler> currentHandlers = handlers.get(e.getClass());

    for (EventHandler handler : currentHandlers) {
      handler.addEvent(e);
    }

  }

  public void addEventHandler(Class<? extends Event> eventToHandle, EventHandler handler) {

    LinkedList<EventHandler> currentHandlers = handlers.get(eventToHandle);

    if (currentHandlers == null) {
      currentHandlers = new LinkedList<EventHandler>();
      handlers.put(eventToHandle, currentHandlers);
    }

    currentHandlers.add(handler);

  }

}
