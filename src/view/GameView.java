package view;

import controller.UIController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameView {

  private static StackPane root;
  private static Canvas chipsHolder;

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

    ImageView boardView =
        new ImageView(new Image(GameView.class.getClassLoader().getResourceAsStream("board.png")));

    chipsHolder = new Canvas();

    chipsHolder.translateXProperty().bind(boardView.layoutXProperty());
    chipsHolder.translateYProperty().bind(boardView.layoutYProperty());

    chipsHolder.widthProperty().bind(boardView.fitWidthProperty());
    chipsHolder.heightProperty().bind(boardView.fitHeightProperty());

    root.getChildren().add(boardView);
    root.getChildren().add(chipsHolder);

    return root;

  }

  public static void placeChip(Circle chip, int column, int row) {

  }

}
