package controller;

import java.util.HashMap;
import event.ChipPlaceEvent;
import event.StopEvent;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import model.Chip;
import view.GameView;

/**
 * @author Rawad Aboudlal
 *
 */
public class UIController {

  // Just ignore this for now... We will need one for each *Event type.
  private static final HashMap<Node, EventHandler<MouseEvent>> MOUSE_EVENT_HANDLERS =
      new HashMap<Node, EventHandler<MouseEvent>>();
  private static final HashMap<Window, EventHandler<WindowEvent>> WINDOW_EVENT_HANDLERS =
      new HashMap<Window, EventHandler<WindowEvent>>();

  public static void addStage(Stage stage) {

    EventHandler<WindowEvent> eventHandler = new EventHandler<WindowEvent>() {
      /**
       * @see javafx.event.EventHandler#handle(javafx.event.Event)
       */
      @Override
      public void handle(WindowEvent event) {
        GameController.addEvent(new StopEvent());
      }
    };

    WINDOW_EVENT_HANDLERS.put(stage, eventHandler);

    stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, eventHandler);

  }

  public static void addChipSpot(Node placeHolder, int col) {

    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {

      private final int column = col;

      /**
       * @see javafx.event.EventHandler#handle(javafx.event.Event)
       */
      @Override
      public void handle(MouseEvent event) {
        GameController.addEvent(new ChipPlaceEvent(column));
      }

    };

    MOUSE_EVENT_HANDLERS.put(placeHolder, eventHandler);

    placeHolder.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);

  }

  public static void chipPlaced(Chip chip, int column, int row) {

    Circle chipView = new Circle(32, chip.getColor());

    addChipSpot(chipView, column);

    Platform.runLater(new Runnable() {
      /**
       * @see java.lang.Runnable#run()
       */
      @Override
      public void run() {
        // GameController.setPaused(true);
        GameView.placeChip(chipView, column, row);
      }
    });

  }

  public static void terminate() {

    for (Node node : MOUSE_EVENT_HANDLERS.keySet()) {
      EventHandler<MouseEvent> eventHandler = MOUSE_EVENT_HANDLERS.get(node);
      node.removeEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    for (Window window : WINDOW_EVENT_HANDLERS.keySet()) {
      EventHandler<WindowEvent> eventHandler = WINDOW_EVENT_HANDLERS.get(window);
      window.removeEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, eventHandler);
    }

  }

}
