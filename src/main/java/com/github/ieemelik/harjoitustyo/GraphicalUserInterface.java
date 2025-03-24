package com.github.ieemelik.harjoitustyo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphicalUserInterface extends Application {
  private final TicketHandler ticketHandler = new TicketHandler();
  private final TicketListView ticketList = new TicketListView(ticketHandler);

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    ticketHandler.writeFile();
  }

  @Override
  public void start(Stage stage) {
    Scene scene = new Scene(ticketList.getListView(), 800, 600);

    stage.setScene(scene);
    stage.setTitle("HelpDesk");
    stage.show();
  }
}
