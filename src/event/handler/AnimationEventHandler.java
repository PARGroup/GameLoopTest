package event.handler;

import event.AnimationEvent;
import event.Event;
import view.animation.Animator;

/**
 * @author Rawad Aboudlal
 *
 */
public class AnimationEventHandler extends EventHandler {

  /**
   * 
   */
  public AnimationEventHandler() {
    super("Animation Thread");
  }

  /**
   * @see event.handler.EventHandler#processEvent(event.Event)
   */
  @Override
  protected void processEvent(Event e) {
    Animator.animate((AnimationEvent) e);
  }

}
