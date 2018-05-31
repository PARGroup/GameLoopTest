package view;

import controller.GameController;
import event.AnimationEvent;
import event.GameEvent;
import event.StopGameEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Rawad Aboudlal
 *
 */
public class ViewController {

  private GameController gameController;

  /**
   * @param gameController
   */
  public ViewController(GameController gameController) {
    super();

    this.gameController = gameController;

  }

  public void initialize() {

  }

  public void addGameEventButton(Button button) {

    button.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
      /**
       * @see javafx.event.EventHandler#handle(javafx.event.Event)
       */
      @Override
      public void handle(ActionEvent event) {
        gameController.getEventHandler().addEvent(new GameEvent());
      }
    });

  }

  public void addAnimationEventButton(Button button) {

    button.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
      /**
       * @see javafx.event.EventHandler#handle(javafx.event.Event)
       */
      @Override
      public void handle(ActionEvent event) {
        gameController.getEventHandler().addEvent(new AnimationEvent(button));
      }
    });

  }

  public void setOnClose(Stage stage) {
    stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
      /**
       * @see javafx.event.EventHandler#handle(javafx.event.Event)
       */
      @Override
      public void handle(WindowEvent event) {
        gameController.getEventHandler().addEvent(new StopGameEvent());
      }
    });
  }

}
