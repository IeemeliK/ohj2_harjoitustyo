package com.github.ieemelik.harjoitustyo;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicketAddView {
  private final Stage stage = new Stage();
  private Scene scene;

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
  private final VBox root = new VBox(ticketTypeSelector, ticketInformationBox);

  TicketAddView() {
    ticketTypeSelector.getItems().addAll("HardwareTicket", "SoftwareTicket");
    ticketTypeSelector.setPromptText("Valitse tiketin tyyppi");
  }
}
