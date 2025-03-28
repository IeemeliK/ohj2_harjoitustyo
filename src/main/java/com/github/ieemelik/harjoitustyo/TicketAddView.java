package com.github.ieemelik.harjoitustyo;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class TicketAddView {
  private final Stage stage = new Stage();
  private TicketHandler ticketHandler;

  private final Label ticketTitleLabel = new Label("Tiketin otsikko");
  private final TextField ticketTitle = new TextField();
  private final VBox ticketTitleBox = new VBox(ticketTitleLabel, ticketTitle);

  private final Label ticketDescriptionLabel = new Label("Tiketin kuvaus");
  private final TextArea ticketDescription = new TextArea();
  private final VBox ticketDescriptionBox = new VBox(ticketDescriptionLabel, ticketDescription);

  private final Label deviceTypeLabel = new Label("Laitteen tyyppi (valinnainen)");
  private final TextField deviceType = new TextField();
  private final VBox deviceTypeBox = new VBox(deviceTypeLabel, deviceType);

  private final Label softwareNameLabel = new Label("Sovelluksen nimi (valinnainen)");
  private final TextField softwareName = new TextField();
  private final VBox softwareNameBox = new VBox(softwareNameLabel, softwareName);

  private final Label errorLogLabel = new Label("Virheloki (valinnainen)");
  private final TextArea errorLog = new TextArea();
  private final VBox errorLogBox = new VBox(errorLogLabel, errorLog);

  private final Button addButton = new Button("Lisää tiketti");
  private final ComboBox<String> ticketTypeSelector = new ComboBox<>();

  private final VBox ticketInformationBox = new VBox();

  TicketAddView(TicketHandler ticketHandler) {
    this.ticketHandler = ticketHandler;

    ticketTypeSelector.getItems().addAll("HardwareTicket", "SoftwareTicket");
    ticketTypeSelector.setPromptText("Valitse tiketin tyyppi");
    ticketTitleLabel.setLabelFor(ticketTitle);

    VBox root = new VBox(ticketTypeSelector, ticketInformationBox);
    root.setSpacing(10);
    root.setPadding(new Insets(40));

    ticketTypeSelector.setMaxWidth(600);
    ticketTitle.setMaxWidth(600);
    ticketDescription.setMaxWidth(600);
    ticketDescription.setWrapText(true);
    softwareName.setMaxWidth(600);
    errorLog.setMaxWidth(600);
    errorLog.setWrapText(true);
    deviceType.setMaxWidth(600);

    ticketInformationBox.setSpacing(10);

    ticketTypeSelector.setOnAction(e -> handleTypeSelection());
    addButton.setOnAction(e -> handleAddButton());

    Scene scene = new Scene(root, 500, 500);
    this.stage.setScene(scene);
    this.stage.setTitle("Add ticket");
  }

  private void handleTypeSelection() {
    String selectedType = ticketTypeSelector.getValue();
    ObservableList<Node> children = ticketInformationBox.getChildren();
    children.clear();

    if (selectedType.equals("HardwareTicket")) {
      children.addAll(ticketTitleBox, ticketDescriptionBox, deviceTypeBox);
    } else if (selectedType.equals("SoftwareTicket")) {
      children.addAll(ticketTitleBox, ticketDescriptionBox, softwareNameBox, errorLogBox);
    }

    children.add(addButton);
  }

  private void handleAddButton() {
    if (ticketTitle.getText().isBlank() || ticketDescription.getText().isBlank()) {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.initOwner(stage);
      alert.setTitle("Otsikko ja kuvaus vaadittu");
      alert.setContentText("Tiketin otsikko ja kuvaus ovat pakollisia kenttiä");
      alert.showAndWait();
      return;
    }

    String ticketType = ticketTypeSelector.getValue();
    Ticket ticketToAdd = null;
    if (ticketType.equals("HardwareTicket")) {
      ticketToAdd = new HardwareTicket(ticketTitle.getText(), ticketDescription.getText());
      if (!(deviceType.getText().isBlank())) ((HardwareTicket) ticketToAdd).setDeviceType(deviceType.getText());
    } else if (ticketType.equals("SoftwareTicket")) {
      ticketToAdd = new SoftwareTicket(ticketTitle.getText(), ticketDescription.getText());
      if (!(softwareName.getText().isBlank())) {
        ((SoftwareTicket) ticketToAdd).setSoftwareName(softwareName.getText());
      }
      if (!(errorLog.getText().isBlank())) {
        ((SoftwareTicket) ticketToAdd).setErrorLog(errorLog.getText());
      }
    }

    if (Objects.nonNull(ticketToAdd)) this.ticketHandler.addTicket(ticketToAdd);
    this.stage.close();
  }

  public Stage getStage() {
    return stage;
  }
}
