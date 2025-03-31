package com.github.ieemelik.harjoitustyo;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.List;

public class TicketUpdatesView {
  private final List<TicketUpdate> updates;
  private final ListView<TicketUpdate> updatesView = new ListView<>();

  TicketUpdatesView(List<TicketUpdate> updates) {
    this.updates = updates;
    if (updates.isEmpty()) {
      return;
    }
    updatesView.addEventFilter(MouseEvent.ANY, e -> new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        event.consume();
      }
    });

    updatesView.setCellFactory(p -> new ListCell<>() {
      @Override
      protected void updateItem(TicketUpdate update, boolean empty) {
        {
          setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
        super.updateItem(update, empty);
        if (empty || update == null) {
          setGraphic(null);
        } else {
          BorderPane view = createUpdateView(update);
          setGraphic(view);
        }
      }
    });

    updatesView.setItems(FXCollections.observableList(this.updates));
  }

  private BorderPane createUpdateView(TicketUpdate update) {
    BorderPane borderPane = new BorderPane();
    Label statusLabel = new Label(update.getStatus().getStatusText());

    HBox topBox = new HBox();
    Text updateText = new Text(update.getUpdateText());
    borderPane.setLeft(updateText);
    borderPane.setTop(statusLabel);
    return borderPane;
  }

  public ListView<TicketUpdate> getUpdatesView() {
    return updatesView;
  }
}
