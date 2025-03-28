package com.github.ieemelik.harjoitustyo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * An abstract-class describing a ticket
 *
 * @author Eemeli Koikkalainen
 * @version 1.0
 */
public abstract class Ticket implements Serializable {
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
   * @see TicketStatus
   */
  Ticket(String title, String description) {
    this.description = description;
    this.title = title;
    this.status = TicketStatus.NEW;
    this.creationDate = new Date();
    this.ticketId = UUID.randomUUID().toString();
  }

  /**
   * {@link Ticket#ticketId}
   */
  public String getTicketId() {
    return ticketId;
  }

  /**
   * {@link Ticket#creationDate}
   */
  public Date getCreationDate() {
    return creationDate;
  }

  /**
   * Formats the creation date of this ticket to "dd/MM/yyyy HH:mm"
   * @return the formatted date
   */
  public String getFormattedCreationDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    return formatter.format(creationDate);
  }

  /**
   * {@link Ticket#creationDate}
   */
  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  /**
   * @return ArrayList of TicketUpdate-objects
   * @see TicketUpdate
   */
  public List<TicketUpdate> getUpdates() {
    return Collections.unmodifiableList(updates);
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  /**
   * {@link Ticket#description}
   */
  public String getDescription() {
    return description;
  }

  /**
   * {@link Ticket#description}
   */
  public void setDescription(String description) {
    this.description = description;
  }


  /**
   * {@link Ticket#title}
   */
  public String getTitle() {
    return title;
  }

  /**
   * {@link Ticket#title}
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * {@link Ticket#status}
   */
  public TicketStatus getStatus() {
    return status;
  }

  /**
   * {@link Ticket#status}
   */
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
   * Adds an update of type TicketUpdate as the first element to this Ticket-objects updates list
   *
   * @param updateText String value describing the update
   * @param status Status value from the TicketStatus enum. This status is saved for each update so the status of each
   *               update can be tracked
   * @see TicketUpdate
   * @see TicketStatus
   */
  protected void addUpdate(String updateText, TicketStatus status) {
    this.setStatus(status);
    this.updates.addFirst(new TicketUpdate(updateText, status));
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Ticket ticket)) return false;
    return Objects.equals(ticketId, ticket.ticketId) && Objects.equals(creationDate, ticket.creationDate)
        && Objects.equals(expiryDate, ticket.expiryDate) && Objects.equals(description, ticket.description)
        && Objects.equals(title, ticket.title) && status == ticket.status && Objects.equals(updates, ticket.updates);
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
