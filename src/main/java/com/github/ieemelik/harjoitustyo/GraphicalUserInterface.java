package com.github.ieemelik.harjoitustyo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;

public class GraphicalUserInterface extends Application {
  private final TicketHandler ticketHandler = new TicketHandler();
  private final TicketListView ticketList = new TicketListView(ticketHandler);
  private final ListView<Ticket> ticketListView = ticketList.getListView();
  private final Button addButton = new Button("Lisää Tiketti");
  protected static final StackPane MAIN_ROOT = new StackPane();
  private static Scene scene;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    ticketHandler.writeFile();
  }

  public static void switchRoot(Pane root) {
    scene.setRoot(root);
  }

  public void handleAddButton() {
    TicketAddView ticketAddView = new TicketAddView(this.ticketHandler);
    Stage addStage = ticketAddView.getStage();
    addStage.show();

    // Disables main stage while ticket adding is showing
    MAIN_ROOT.disableProperty().bind(addStage.showingProperty());
  }

  @Override
  public void start(Stage primaryStage) {
    StackPane.setAlignment(this.addButton, Pos.BOTTOM_CENTER);
    this.addButton.setTranslateY(-40);
    this.addButton.setOnAction(e -> handleAddButton());
    this.addButton.getStyleClass().add("addButton");

    MAIN_ROOT.getChildren().addAll(this.ticketListView, addButton);
    MAIN_ROOT.getStyleClass().add("mainRoot");
    MAIN_ROOT.getStylesheets().add(
        Objects.requireNonNull(GraphicalUserInterface.class.getResource("style.css")).toExternalForm());

    scene = new Scene(MAIN_ROOT, 800, 600);
    primaryStage.setScene(scene);
    primaryStage.setTitle("HelpDesk");
    primaryStage.setOnCloseRequest(e -> Platform.exit());
    primaryStage.show();
  }
}
