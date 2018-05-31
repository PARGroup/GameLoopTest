package main;

import controller.GameController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.GameView;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameLoopTest extends Application {

  /**
   * @param args
   */
  public static void main(String[] args) {

    Application.launch(args);

  }

  /**
   * @see javafx.application.Application#start(javafx.stage.Stage)
   */
  @Override
  public void start(Stage primaryStage) throws Exception {

    GameController.startGame();
    GameView.createView(primaryStage);

  }

}
