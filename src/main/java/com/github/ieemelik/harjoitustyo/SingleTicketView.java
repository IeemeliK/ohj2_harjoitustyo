package com.github.ieemelik.harjoitustyo;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class SingleTicketView {
  private final Ticket ticket;
  private final Text text = new Text("TOIMIIKO TÄÄ");
  private final Scene scene;

  public SingleTicketView(Ticket ticket) {
    this.ticket = ticket;
    StackPane root = new StackPane(this.text);
    this.scene = new Scene(root, 800, 600);
  }

  public Scene getScene() {
    return scene;
  }
}
