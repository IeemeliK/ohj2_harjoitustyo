package com.github.ieemelik.harjoitustyo;

import java.util.Date;
import java.util.UUID;

/**
 *
 */
public class TicketUpdate {
  private final String updateId;
  private final Date updateDate;
  private final TicketStatus status;
  private String updateText;

  TicketUpdate(String updateText, TicketStatus status) {
    this.updateId = UUID.randomUUID().toString();
    this.updateDate = new Date();

    this.updateText = updateText;
    this.status = status;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public String getUpdateText() {
    return updateText;
  }

  public void setUpdateText(String updateText) {
    this.updateText = updateText;
  }

  public String getUpdateId() {
    return updateId;
  }

  public TicketStatus getStatus() {
    return status;
  }
}
