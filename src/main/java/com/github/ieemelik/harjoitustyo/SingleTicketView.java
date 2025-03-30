package com.github.ieemelik.harjoitustyo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SingleTicketView {
  private final Ticket ticket;
  private final TicketHandler ticketHandler;

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
  private final BorderPane root = new BorderPane();

  private final Button modifyButton = new Button("Muokkaa");
  private final Button saveButton = new Button("Tallenna");
  private final HBox buttonBox = new HBox();

  private BooleanProperty editable = new SimpleBooleanProperty(false);

  public SingleTicketView(Ticket ticket, TicketHandler ticketHandler) {
    this.ticket = ticket;
    this.ticketHandler = ticketHandler;

    this.buildGeneralFields(ticket);

    if (ticket instanceof HardwareTicket hwTicket) {
      buildHardwareTicketFields(hwTicket);
    } else if (ticket instanceof SoftwareTicket swTicket) {
      buildSoftwareTicketFields(swTicket);
    }

    ticketTitle.editableProperty().bind(this.editable);
    ticketDescription.editableProperty().bind(this.editable);
    deviceType.editableProperty().bind(this.editable);
    softwareName.editableProperty().bind(this.editable);
    errorLog.editableProperty().bind(this.editable);

    Button backButton = new Button("Takaisin");
    Region spacer = new Region();

    saveButton.setOnAction(event -> handleSaveButton());
    modifyButton.setOnAction(e -> handleModifyButton());

    buttonBox.getChildren().addAll(backButton, spacer, modifyButton);
    HBox.setHgrow(spacer, Priority.ALWAYS);
    buttonBox.setPadding(new Insets(15, 70, 0, 70));

    backButton.setOnAction(e -> {
      GraphicalUserInterface.switchRoot(GraphicalUserInterface.MAIN_ROOT);
    });

    ticketInfo.setSpacing(10);
    ticketInfo.setPadding(new Insets(20, 70, 0, 70));

    root.setTop(buttonBox);
    root.setCenter(ticketInfo);
  }

  private void buildGeneralFields(Ticket ticket) {
    ticketTitle.setText(ticket.getTitle());
    ticketTitle.setEditable(false);

    ticketDescription.setText(ticket.getDescription());
    ticketDescription.setEditable(false);

    ticketInfo.getChildren().addAll(titleBox, descriptionBox);
  }

  private void buildHardwareTicketFields(HardwareTicket ticket) {
    deviceType.setText(ticket.getDeviceType());
    deviceType.setEditable(false);
    ticketInfo.getChildren().add(deviceTypeBox);
  }

  private void buildSoftwareTicketFields(SoftwareTicket ticket) {
    softwareName.setText(ticket.getSoftwareName());
    softwareName.setEditable(false);

    errorLog.setText(ticket.getErrorLog());
    errorLog.setEditable(false);

    ticketInfo.getChildren().addAll(softwareNameBox, errorLogBox);
  }

  private void handleModifyButton() {
    this.editable.set(true);
    buttonBox.getChildren().remove(modifyButton);
    buttonBox.getChildren().add(saveButton);
  }

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

    if (ticket instanceof HardwareTicket hwTicket) {
      hwTicket.setDeviceType(this.deviceType.getText());
    } else if (ticket instanceof SoftwareTicket swTicket) {
      swTicket.setSoftwareName(this.softwareName.getText());
      swTicket.setErrorLog(this.errorLog.getText());
    }

    this.editable.set(false);
    buttonBox.getChildren().remove(saveButton);
    buttonBox.getChildren().add(modifyButton);

    ticketHandler.notifyObservers();
  }

  public BorderPane getRoot() {
    return root;
  }
}
