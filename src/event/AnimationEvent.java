package event;

import javafx.scene.Node;

/**
 * @author Rawad Aboudlal
 *
 */
public class AnimationEvent extends Event {

  private final Node node;

  /**
   * @param node
   */
  public AnimationEvent(Node node) {
    super();

    this.node = node;

  }

  /**
   * @return the node
   */
  public Node getNode() {
    return node;
  }

}
