package com.github.ieemelik.harjoitustyo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GraphicalUserInterface extends Application {
  private final TicketHandler ticketHandler = new TicketHandler();
  private final TicketListView ticketList = new TicketListView(ticketHandler);
  private final ListView<Ticket> ticketListView = ticketList.getListView();
  private final Button addButton = new Button("Lisää Tiketti");
  private final StackPane root = new StackPane();

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    ticketHandler.writeFile();
  }

  public void handleAddButton() {
    TicketAddView ticketAddView = new TicketAddView(this.ticketHandler);
    Stage addStage = ticketAddView.getStage();
    addStage.show();

    // Disables main stage while ticket adding is showing
    this.root.disableProperty().bind(addStage.showingProperty());
  }

  @Override
  public void start(Stage primaryStage) {
    StackPane.setAlignment(this.addButton, Pos.BOTTOM_RIGHT);
    this.addButton.setTranslateY(-40);
    this.addButton.setTranslateX(-40);
    this.addButton.setOnAction(e -> handleAddButton());
    this.root.getChildren().addAll(this.ticketListView, addButton);

    Scene scene = new Scene(this.root, 800, 600);
    primaryStage.setScene(scene);
    primaryStage.setTitle("HelpDesk");
    primaryStage.setOnCloseRequest(e -> Platform.exit());
    primaryStage.show();
  }
}
