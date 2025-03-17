package com.github.ieemelik.harjoitustyo;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public abstract class Ticket {
  private String ticketId;

  private Date creationDate;
  private Date expiryDate;
  
  private String description;
  private String title;

  private TicketStatus status;
  private final ArrayList<TicketUpdate> updates = new ArrayList<>();

  Ticket(String description, String title) {
    this.description = description;
    this.title = title;
    this.status = TicketStatus.NEW;
    this.creationDate = new Date();
    this.ticketId = UUID.randomUUID().toString();
  }

  public String getTicketId() {
    return ticketId;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public ArrayList<TicketUpdate> getUpdates() {
    return updates;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public TicketStatus getStatus() {
    return status;
  }

  public void setStatus(TicketStatus status) {
    this.status = status;
  }

  public void setTicketId(String ticketId) {
    this.ticketId = ticketId;
  }

  private void resolve(String resolveText) {
    this.setStatus(TicketStatus.RESOLVED);
    this.addUpdate(resolveText, TicketStatus.RESOLVED);
  }

  private void addUpdate(String updateText, TicketStatus status) {
    this.updates.add(new TicketUpdate(updateText, status));
  }
}
