package com.github.ieemelik.harjoitustyo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.Objects;

import static com.github.ieemelik.harjoitustyo.GraphicalUserInterface.switchRoot;
import static com.github.ieemelik.harjoitustyo.GraphicalUserInterface.MAIN_ROOT;

public class SingleTicketView {
  private final Ticket ticket;
  private final TicketHandler ticketHandler;

  private final BorderPane root = new BorderPane();

  private final Label statusLabel = new Label("Tiketin status");
  private final ComboBox<TicketStatus> statusComboBox = new ComboBox<>();
  private final VBox statusBox = new VBox(statusLabel, statusComboBox);

  private final Label titleLabel = new Label("Tiketin otsikko");
  private final TextField ticketTitle = new TextField();
  private final VBox titleBox = new VBox(titleLabel, ticketTitle);

  private final Label descriptionLabel = new Label("Tiketin kuvaus");
  private final TextArea ticketDescription = new TextArea();
  private final VBox descriptionBox = new VBox(descriptionLabel, ticketDescription);

  private final Label deviceTypeLabel = new Label("Laitteen tyyppi");
  private final TextField deviceType = new TextField();
  private final VBox deviceTypeBox = new VBox(deviceTypeLabel, deviceType);

  private final Label softwareNameLabel = new Label("Ohjelmiston nimi");
  private final TextField softwareName = new TextField();
  private final VBox softwareNameBox = new VBox(softwareNameLabel, softwareName);

  private final Label errorLogLabel = new Label("Virheloki");
  private final TextArea errorLog = new TextArea();
  private final VBox errorLogBox = new VBox(errorLogLabel, errorLog);

  private final VBox ticketInfo = new VBox();

  private final Button modifyButton = new Button("Muokkaa");
  private final Button saveButton = new Button("Tallenna");
  private final HBox buttonBox = new HBox();

  /**
   * An observable value to flip editable fields dynamically
   */
  private final BooleanProperty editable = new SimpleBooleanProperty(false);

  /**
   * Constructs a view for reading and editing a ticket
   *
   * @param ticket Ticket which is used to create this view
   * @param ticketHandler ticketHandler is used to update ticket list view of the updates
   */
  public SingleTicketView(Ticket ticket, TicketHandler ticketHandler) {
    this.ticket = ticket;
    this.ticketHandler = ticketHandler;

    statusComboBox.getSelectionModel().select(ticket.getStatus());
    statusComboBox.setCellFactory(p -> createStatusCell());
    statusComboBox.setButtonCell(createStatusCell());

    this.buildGeneralFields(ticket);

    if (ticket instanceof HardwareTicket hwTicket) {
      buildHardwareTicketFields(hwTicket);
    } else if (ticket instanceof SoftwareTicket swTicket) {
      buildSoftwareTicketFields(swTicket);
    }

    statusComboBox.disableProperty().bind(this.editable.not());
    ticketTitle.editableProperty().bind(this.editable);
    ticketDescription.editableProperty().bind(this.editable);
    deviceType.editableProperty().bind(this.editable);
    softwareName.editableProperty().bind(this.editable);
    errorLog.editableProperty().bind(this.editable);

    this.root.getStyleClass().add("singleViewRoot");
    this.root.getStylesheets().add(
        Objects.requireNonNull(this.getClass().getResource("single-ticket-view.css")).toExternalForm()
    );
  }

  /**
   * Creates cells to be used for status combo box values
   */
  private ListCell<TicketStatus> createStatusCell() {
    return new ListCell<>() {
      @Override
      protected void updateItem(TicketStatus status, boolean empty) {
        super.updateItem(status, empty);
        if (empty || status == null) {
          setText(null);
        } else {
          setText(status.getStatusText());
        }
      }
    };
  }

  /**
   * Builds general data fields for a ticket
   */
  private void buildGeneralFields(Ticket ticket) {
    statusComboBox.setItems(FXCollections.observableArrayList(TicketStatus.values()));

    ticketTitle.setText(ticket.getTitle());

    ticketDescription.setText(ticket.getDescription());
    ticketDescription.setWrapText(true);

    Button backButton = new Button("Takaisin");
    backButton.setOnAction(e -> switchRoot(MAIN_ROOT));

    Region spacer = new Region();

    saveButton.setOnAction(event -> handleSaveButton());
    modifyButton.setOnAction(e -> handleModifyButton());

    buttonBox.getChildren().addAll(backButton, spacer, modifyButton);
    HBox.setHgrow(spacer, Priority.ALWAYS);
    buttonBox.setPadding(new Insets(15, 70, 0, 70));

    ScrollPane ticketInfoScroll = new ScrollPane(ticketInfo);
    ticketInfoScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    ticketInfoScroll.setFitToWidth(true);
    ticketInfoScroll.setStyle("-fx-background-color: transparent;");

    ticketInfo.setSpacing(10);
    ticketInfo.setPadding(new Insets(20, 70, 20, 70));

    ticketInfo.getChildren().addAll(statusBox, titleBox, descriptionBox);
    root.setTop(buttonBox);
    root.setCenter(ticketInfoScroll);
  }

  /**
   * Builds data fields specific to a HardwareTicket
   */
  private void buildHardwareTicketFields(HardwareTicket ticket) {
    deviceType.setText(ticket.getDeviceType());
    ticketInfo.getChildren().add(deviceTypeBox);
  }

  /**
   * Builds data fields specific to a SoftwareTicket
   */
  private void buildSoftwareTicketFields(SoftwareTicket ticket) {
    softwareName.setText(ticket.getSoftwareName());

    errorLog.setText(ticket.getErrorLog());
    errorLog.setWrapText(true);

    ticketInfo.getChildren().addAll(softwareNameBox, errorLogBox);
  }

  /**
   * Makes fields editable and swaps the modify button for the save button
   */
  private void handleModifyButton() {
    this.editable.set(true);
    buttonBox.getChildren().remove(modifyButton);
    buttonBox.getChildren().add(saveButton);
  }

  /**
   * Checks if title and description are blank and shows an error if they are. If fields are properly filled
   * attempts to save all fields and notifies observables of the change
   */
  private void handleSaveButton() {
    if (ticketTitle.getText().isBlank() || ticketDescription.getText().isBlank()) {
      this.ticketTitle.setText(this.ticket.getTitle());
      this.ticketDescription.setText(this.ticket.getDescription());
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Otsikko ja kuvaus tyhjiä");
      alert.setContentText("Tiketin otsikko ja kuvaus eivät saa olla tyhjiä");
      alert.showAndWait();
      return;
    }

    ticket.setTitle(this.ticketTitle.getText());
    ticket.setDescription(this.ticketDescription.getText());
    ticket.setStatus(this.statusComboBox.getSelectionModel().getSelectedItem());

    if (ticket instanceof HardwareTicket hwTicket) {
      hwTicket.setDeviceType(this.deviceType.getText());
    } else if (ticket instanceof SoftwareTicket swTicket) {
      swTicket.setSoftwareName(this.softwareName.getText());
      swTicket.setErrorLog(this.errorLog.getText());
    }

    this.editable.set(false);
    buttonBox.getChildren().remove(saveButton);
    buttonBox.getChildren().add(modifyButton);

    ticketHandler.writeFile();
    ticketHandler.notifyObservers();
  }

  public BorderPane getRoot() {
    return root;
  }
}
