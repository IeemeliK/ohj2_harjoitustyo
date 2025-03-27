package com.github.ieemelik.harjoitustyo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GraphicalUserInterface extends Application {
  private final TicketHandler ticketHandler = new TicketHandler();
  private final TicketListView ticketList = new TicketListView(ticketHandler);
  private final ListView<Ticket> ticketListView = ticketList.getListView();
  private final Button addButton = new Button("Lisää Tiketti");

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    ticketHandler.writeFile();
  }

  public void handleAddButton() {
    Stage addStage = new Stage();
    TextField ticketName = new TextField();
    StackPane stackPane = new StackPane();
    stackPane.getChildren().add(ticketName);
    Scene scene = new Scene(stackPane, 500, 500);
    addStage.setScene(scene);
    addStage.show();
  }

  @Override
  public void start(Stage primaryStage) {
    StackPane root = new StackPane();
    StackPane.setAlignment(this.addButton, Pos.BOTTOM_RIGHT);
    this.addButton.setTranslateY(-40);
    this.addButton.setTranslateX(-40);
    this.addButton.setOnAction(e -> handleAddButton());
    root.getChildren().addAll(this.ticketListView, addButton);

    Scene scene = new Scene(root, 800, 600);
    primaryStage.setScene(scene);
    primaryStage.setTitle("HelpDesk");
    primaryStage.show();
  }
}
