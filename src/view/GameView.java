package view;

import controller.GameController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameView extends Application {

  private Stage stage;

  private ViewController viewController;
  private GameController gameController;

  /**
   * @see javafx.application.Application#start(javafx.stage.Stage)
   */
  @Override
  public void start(Stage primaryStage) throws Exception {

    gameController = new GameController();
    viewController = new ViewController(gameController);

    viewController.initialize();
    gameController.initialize();

    stage = primaryStage;

    viewController.setOnClose(stage);

    Parent root = createRoot();

    Scene scene = new Scene(root);

    scene.setRoot(root);

    stage.setScene(scene);
    stage.setWidth(640);
    stage.setHeight(480);
    stage.setTitle("Test");
    stage.show();

  }

  private Parent createRoot() {

    VBox root = new VBox();

    Button gameButton = new Button("Test Something");
    viewController.addGameEventButton(gameButton);

    Button animationButton = new Button("Make me bounce");
    viewController.addAnimationEventButton(animationButton);

    root.getChildren().add(gameButton);
    root.getChildren().add(animationButton);

    return root;

  }

}
