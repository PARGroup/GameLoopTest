package view;

import controller.GameController;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameView {

  public static void createView(Stage stage) {

    stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
      /**
       * @see javafx.event.EventHandler#handle(javafx.event.Event)
       */
      @Override
      public void handle(WindowEvent event) {
        GameController.stopGame();
      }
    });

    stage.setWidth(640);
    stage.setHeight(480);
    stage.setTitle("Test");
    stage.show();

  }

}
