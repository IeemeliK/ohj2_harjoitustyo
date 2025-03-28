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
  private final ListView<Ticket> listView = new ListView<>();

  TicketListView(TicketHandler ticketHandler) {
    this.ticketHandler = ticketHandler;
    this.tickets = FXCollections.observableArrayList(ticketHandler.getTickets());
    listView.getStylesheets().add(
        Objects.requireNonNull(getClass().getResource("ticket-list-view.css")).toExternalForm()
    );

    ticketHandler.addObserver((tickets) -> {
      this.listView.setItems(FXCollections.observableList(tickets));
    });

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

  /**
   * Creates a graphical item to add as a list cell
   *
   * @param ticket A ticket to be used to create the element
   * @return the graphical element
   */
  private HBox createListItem(Ticket ticket) {
    TicketStatus status = ticket.getStatus();
    HBox hBox = new HBox(7);
    Label statusLabel = new Label(status.getStatusText());
    Text ticketTitle = new Text(ticket.getTitle());
    Text ticketDate = new Text(ticket.getFormattedCreationDate());
    Button deleteButton = new Button("POISTA");
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

  /**
   * Attempts to remove given ticket from ticketHandler list (and updates this listview through observers)
   *
   * @param ticket the ticket to remove
   * @see TicketHandler#removeTicket(Ticket)
   */
  private void deleteHandler(Ticket ticket) {
   this.ticketHandler.removeTicket(ticket);
  }

  public ListView<Ticket> getListView() {
    return listView;
  }
}
