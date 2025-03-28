package com.github.ieemelik.harjoitustyo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
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
  private Scene scene;
  private Stage primaryStage;

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

  private final ChangeListener<Ticket> ticketSelectionListener = (obs, ov, nv) -> {
    SingleTicketView ticketView = new SingleTicketView(nv);
    Scene testScene = ticketView.getScene();
    primaryStage.setScene(testScene);
  };

  @Override
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;

    StackPane.setAlignment(this.addButton, Pos.BOTTOM_RIGHT);
    this.addButton.setTranslateY(-40);
    this.addButton.setTranslateX(-40);
    this.addButton.setOnAction(e -> handleAddButton());
    this.ticketListView.getSelectionModel().selectedItemProperty().addListener(ticketSelectionListener);
    this.root.getChildren().addAll(this.ticketListView, addButton);

    this.scene = new Scene(this.root, 800, 600);
    this.primaryStage.setScene(scene);
    this.primaryStage.setTitle("HelpDesk");
    this.primaryStage.setOnCloseRequest(e -> Platform.exit());
    this.primaryStage.show();
  }
}
