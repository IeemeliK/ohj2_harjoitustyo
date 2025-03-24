package com.github.ieemelik.harjoitustyo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.util.Objects;


public class TicketListView {
  private final TicketHandler ticketHandler;
  private final ObservableList<Ticket> tickets;
  protected final ListView<Ticket> listView = new ListView<>();

  TicketListView(TicketHandler ticketHandler) {
    this.ticketHandler = ticketHandler;
    this.tickets = FXCollections.observableArrayList(ticketHandler.getTickets());
    listView.getStylesheets().add(
        Objects.requireNonNull(getClass().getResource("ticket-list-view.css")).toExternalForm()
    );
    listView.setCellFactory(p -> new ListCell<>() {
      {
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
      }

      @Override
      protected void updateItem(Ticket ticket, boolean empty) {
        super.updateItem(ticket, empty);
        if (empty || ticket == null) {
          setGraphic(null);
          setStyle("");
          getStyleClass().removeAll("custom-list-cell");
          getStyleClass().add("empty");
        } else {
          setGraphic(createListItem(ticket));

          if (!getStyleClass().contains("custom-list-cell")) {
            getStyleClass().add("custom-list-cell");
          }
          getStyleClass().removeAll("empty");
        }
      }
    });

    listView.setItems(this.tickets);
  }

  private HBox createListItem(Ticket ticket) {
    TicketStatus status = ticket.getStatus();
    HBox hBox = new HBox(7);
    Label statusLabel = new Label(status.getStatusText());
    Text ticketTitle = new Text(ticket.getTitle());
    Text ticketDate = new Text(ticket.getFormattedCreationDate());
    Button deleteButton = new Button("DELETE");
    Region spacer = new Region();

    deleteButton.setOnAction(e -> deleteHandler(ticket));

    deleteButton.getStyleClass().add("delete-button");
    statusLabel.getStyleClass().add("ticket-status");
    ticketTitle.getStyleClass().add("ticket-title");
    ticketDate.getStyleClass().add("ticket-date");

    statusLabel.setStyle("-fx-background-color: " + status.getStatusColor() + ";");

    HBox.setHgrow(spacer, Priority.ALWAYS);

    hBox.setAlignment(Pos.CENTER_LEFT);
    hBox.getChildren().addAll(ticketTitle, ticketDate, spacer, statusLabel, deleteButton);

    return hBox;
  }

  private void deleteHandler(Ticket ticket) {
    if (this.ticketHandler.removeTicket(ticket)) {
      this.tickets.remove(ticket);
    }
  }

  public ListView<Ticket> getListView() {
    return listView;
  }
}
