package com.github.ieemelik.harjoitustyo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * A class to wrap a ticket update
 *
 * @author Eemeli Koikkalainen
 * @version 1.0
 */
public class TicketUpdate implements Serializable {
  /**
   * Id for the update, generated on object creation
   */
  private final String updateId;
  /**
   * Date for this object, generated on object creation
   */
  private final Date updateDate;
  /**
   * The status for this update object
   */
  private final TicketStatus status;
  /**
   * A String-value describing this update
   */
  private String updateText;

  /**
   * @param updateText String value describing this update.
   * @param status The status of the ticket at the time of this update
   */
  TicketUpdate(String updateText, TicketStatus status) {
    this.updateId = UUID.randomUUID().toString();
    this.updateDate = new Date();
    this.updateText = updateText;
    this.status = status;
  }

  /**
   * {@link TicketUpdate#updateDate}
   */
  public Date getUpdateDate() {
    return updateDate;
  }

  /**
   * {@link TicketUpdate#updateText}
   */
  public String getUpdateText() {
    return updateText;
  }

  /**
   * {@link TicketUpdate#updateText}
   */
  public void setUpdateText(String updateText) {
    this.updateText = updateText;
  }

  /**
   * {@link TicketUpdate#updateId}
   */
  public String getUpdateId() {
    return updateId;
  }

  /**
   * {@link TicketUpdate#status}
   */
  public TicketStatus getStatus() {
    return status;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof TicketUpdate that)) return false;
    return Objects.equals(updateId, that.updateId) && Objects.equals(updateDate, that.updateDate)
        && status == that.status && Objects.equals(updateText, that.updateText);
  }

  @Override
  public String toString() {
    return "TicketUpdate{" +
        "updateId='" + updateId + '\'' +
        ", updateDate=" + updateDate +
        ", status=" + status +
        ", updateText='" + updateText + '\'' +
        '}';
  }
}
