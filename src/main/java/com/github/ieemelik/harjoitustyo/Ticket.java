package com.github.ieemelik.harjoitustyo;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * An abstract-class describing a ticket
 *
 * @author Eemeli Koikkalainen
 * @version 1.0
 */
public abstract class Ticket {
  /**
   * An id is generated for every ticket automatically using UUID.randomUUID
   */
  private final String ticketId;

  /**
   * The date this ticket was created. Generated on object creation
   */
  private Date creationDate;
  /**
   *
   */
  private Date expiryDate;

  /**
   * String-type description of the ticket
   */
  private String description;
  /**
   * String-type title of the ticket
   */
  private String title;

  /**
   * Status of the ticket. Status values come from the TicketStatus enum.
   * @see TicketStatus
   */
  private TicketStatus status;
  private final ArrayList<TicketUpdate> updates = new ArrayList<>();

  /**
   * This constructor sets the description and title of the Ticket-object to the given parameters and sets the status to
   * TicketStatus.NEW. This also generates a random UUID as ticketId and current date as creationDate
   *
   * @param description A String-value describing this ticket
   * @param title A String-value to be used as a title for this ticket
   */
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

  /**
   * @return ArrayList of TicketUpdate-objects
   * @see TicketUpdate
   */
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

  /**
   * A helper method to resolve calling Ticket-object
   *
   * @param resolveText A text explaining how or why this ticket was resolved. This creates an update using the addUpdate-
   *                    method and sets the ticket status to resolved
   * @see Ticket#addUpdate(String, TicketStatus)
   */
  private void resolve(String resolveText) {
    this.setStatus(TicketStatus.RESOLVED);
    this.addUpdate(resolveText, TicketStatus.RESOLVED);
  }

  /**
   * Adds an update of type TicketUpdate to this Ticket-objects updates list
   *
   * @param updateText String value describing the update
   * @param status Status value from the TicketStatus enum. This status is saved for each update so the status of each
   *               update can be tracked
   * @see TicketUpdate
   * @see TicketStatus
   */
  private void addUpdate(String updateText, TicketStatus status) {
    this.updates.add(new TicketUpdate(updateText, status));
  }

  @Override
  public String toString() {
    return "Ticket{" +
        "status=" + status +
        ", ticketId='" + ticketId + '\'' +
        ", creationDate=" + creationDate +
        ", expiryDate=" + expiryDate +
        ", description='" + description + '\'' +
        ", title='" + title + '\'' +
        '}';
  }
}
