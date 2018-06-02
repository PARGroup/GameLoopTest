package view;

import controller.UIController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameView {

  private static StackPane root;
  private static GridPane grid;

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
    grid = new GridPane();
    grid.setGridLinesVisible(true);

    for (int i = 0; i < 7; i++) {
      ColumnConstraints colConstraint = new ColumnConstraints(64);
      colConstraint.setHgrow(Priority.ALWAYS);
      colConstraint.setFillWidth(true);
      // grid.getColumnConstraints().add(colConstraint);
    }

    for (int i = 0; i < 6; i++) {
      RowConstraints rowConstraint = new RowConstraints(64);
      rowConstraint.setVgrow(Priority.ALWAYS);
      rowConstraint.setFillHeight(true);
      // grid.getRowConstraints().add(rowConstraint);
    }

    for (int row = 0; row < 6; row++) {
      for (int col = 0; col < 7; col++) {
        Rectangle placeHolder = new Rectangle();
        placeHolder.setOpacity(0);
        placeHolder.setFill(Color.RED);
        placeHolder.setWidth(64);
        placeHolder.setHeight(64);
        UIController.addChipSpot(placeHolder, col);
        grid.add(placeHolder, col, row);
      }
    }

    grid.setPadding(new Insets(8, 0, 0, 8));

    grid.translateXProperty().bind(boardView.layoutXProperty());
    grid.translateYProperty().bind(boardView.layoutYProperty());

    grid.prefWidthProperty().bind(boardView.fitWidthProperty());
    grid.prefHeightProperty().bind(boardView.fitHeightProperty());

    grid.setHgap(8);
    grid.setVgap(8);

    root.getChildren().add(boardView);
    root.getChildren().add(grid);

    return root;

  }

  public static void placeChip(Circle chip, int column, int row) {

    grid.getChildren().remove(getNodeByColumnRowIndex(column, row));
    grid.add(chip, column, row);

  }

  private static Node getNodeByColumnRowIndex(int column, int row) {

    for (Node node : grid.getChildren()) {

      Integer colIndex = GridPane.getColumnIndex(node);
      Integer rowIndex = GridPane.getRowIndex(node);

      // Setting grid lines adds a Group object which breaks this.
      if (colIndex == null || rowIndex == null) {
        continue;
      }

      if (colIndex == column && rowIndex == row) {
        return node;
      }
    }

    throw new IllegalStateException("The GridPane should not have any empty cells.");

  }

}
