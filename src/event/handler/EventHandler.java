package event.handler;

import java.util.LinkedList;
import java.util.Queue;
import event.Event;

/**
 * @author Rawad Aboudlal
 *
 */
public abstract class EventHandler implements Runnable {

  private final String name;

  private Queue<Event> events = new LinkedList<Event>();

  private boolean running;

  /**
   * @param name
   */
  public EventHandler(String name) {
    super();

    this.name = name;

  }

  public void start(boolean daemon) {

    running = true;

    Thread t = new Thread(this, name);
    t.setDaemon(daemon);
    t.start();

  }

  public void start() {
    this.start(true);
  }

  public synchronized void stop() {

    running = false;

    int eventsCleared = events.size();

    events.clear();

    System.out.printf("%s events cleared from event handler on this thread: %s.\n", eventsCleared,
        name);

  }

  /**
   * @see java.lang.Runnable#run()
   */
  @Override
  public final void run() {

    while (running) {

      Event e;

      synchronized (this) {
        e = events.poll();
      }

      while (e != null) {

        synchronized (this) {

          processEvent(e);
          e = events.poll();

        }

      }

    }

  }

  protected abstract void processEvent(Event e);

  public synchronized void addEvent(Event e) {
    events.add(e);
  }

}
