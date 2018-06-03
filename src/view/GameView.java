package view;

import controller.UIController;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameView {

  private static StackPane root;
  private static Pane chipsHolder;
  private static ImageView boardView;

  private static final String BOARD = "board-trans-yellow.png";
  
  public static void createView(Stage stage) {

    root = createRoot();

    Scene scene = new Scene(root);
    
    UIController.addStage(stage);

    stage.setScene(scene);
    stage.sizeToScene();
    stage.setTitle("Test");
    stage.show();
    stage.setMinWidth(stage.getWidth());
    stage.setMinHeight(stage.getHeight());

  }

  private static StackPane createRoot() {

    StackPane root = new StackPane();

    boardView =
        new ImageView(new Image(GameView.class.getClassLoader().getResourceAsStream(BOARD)));

    chipsHolder = new Pane();
    UIController.addChipsHolder(chipsHolder);

    chipsHolder.translateXProperty().bind(boardView.layoutXProperty());
    chipsHolder.translateYProperty().bind(boardView.layoutYProperty());

    chipsHolder.prefWidthProperty().bind(boardView.fitWidthProperty());
    chipsHolder.prefHeightProperty().bind(boardView.fitHeightProperty());

    root.getChildren().add(boardView);
    root.getChildren().add(chipsHolder);

    return root;

  }

  public static void placeChip(Circle chip, int x, int startY, int endY) {

    TranslateTransition translation = new TranslateTransition(Duration.millis(100), chip);
    translation.setFromX(x);
    translation.setFromY(startY);
    translation.setToX(x);
    translation.setToY(endY);

    translation.setOnFinished(new EventHandler<ActionEvent>() {
      /**
       * @see javafx.event.EventHandler#handle(javafx.event.Event)
       */
      @Override
      public void handle(ActionEvent event) {
        UIController.onAnimationFinished();
      }
    });

    chipsHolder.getChildren().add(chip);
    translation.playFromStart();

  }

  public static int getBoardWidth() {
    return (int) boardView.getFitWidth();
  }

  public static int getBoardHeight() {
    return (int) boardView.getFitHeight();
  }

}
