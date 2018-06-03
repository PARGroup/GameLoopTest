package controller;

import java.util.HashMap;
import event.ChipPlaceEvent;
import event.StopEvent;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import model.Chip;
import model.ViewConfig;
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

  private static ViewConfig viewConfig;

  public static void initialize() {

    viewConfig = new ViewConfig();

    viewConfig.setChipRadius(32);
    viewConfig.setVgap(8);
    viewConfig.setHgap(8);

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

  public static void addChipsHolder(Pane chipsHolder) {

    EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {

      /**
       * @see javafx.event.EventHandler#handle(javafx.event.Event)
       */
      @Override
      public void handle(MouseEvent event) {

        int x = (int) event.getX();

        int column = x / (viewConfig.getChipRadius() * 2 + viewConfig.getHgap());

        GameController.addEvent(new ChipPlaceEvent(column));

      }

    };

    MOUSE_EVENT_HANDLERS.put(chipsHolder, clickHandler);
    chipsHolder.addEventHandler(MouseEvent.MOUSE_CLICKED, clickHandler);

  }

  public static void chipPlaced(Chip chip, int column, int row) {

    Circle chipView = new Circle(viewConfig.getChipRadius(), chip.getColor());

    final int x = column * (viewConfig.getChipRadius() * 2 + viewConfig.getHgap())
        + viewConfig.getChipRadius() + viewConfig.getHgap();
    // vgap * 2 causes a little bounce effect for the top-most chip only.
    final int y = viewConfig.getVgap() * 2 + viewConfig.getChipRadius();
    final int endY = row * (viewConfig.getChipRadius() * 2 + viewConfig.getVgap())
        + viewConfig.getChipRadius() + viewConfig.getVgap();

    GameController.setPaused(true);

    Platform.runLater(new Runnable() {
      /**
       * @see java.lang.Runnable#run()
       */
      @Override
      public void run() {
        GameView.placeChip(chipView, x, y, endY);
      }
    });

  }

  public static void onAnimationFinished() {
    GameController.setPaused(false);
  }

}
