package view.animation;

import java.util.HashMap;
import event.AnimationEvent;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * @author Rawad Aboudlal
 *
 */
public class Animator {

  private static final HashMap<Node, Animation> ANIMATIONS = new HashMap<Node, Animation>();

  public static void animate(AnimationEvent e) {

    Node node = e.getNode();

    Animation animation = ANIMATIONS.get(node);

    if (animation != null && animation.getStatus() == Animation.Status.RUNNING) {
      return;
    }

    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(400));
    scaleTransition.setNode(node);
    scaleTransition.setToX(1.5);
    scaleTransition.setToY(1.5);
    scaleTransition.setAutoReverse(true);
    scaleTransition.setCycleCount(2);

    ANIMATIONS.put(node, scaleTransition);

    Platform.runLater(new Runnable() {
      /**
       * @see java.lang.Runnable#run()
       */
      @Override
      public void run() {
        scaleTransition.play();
      }
    });

  }

}
