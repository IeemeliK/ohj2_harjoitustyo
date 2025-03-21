package com.github.ieemelik.harjoitustyo;

import java.io.Serializable;
import java.util.Objects;

/**
 * A class for software related tickets
 *
 * @author Eemeli Koikkalainen
 * @version 1.0
 */
public class SoftwareTicket extends Ticket implements Serializable {
  private String softwareName;
  private String errorLog;

  SoftwareTicket(String description, String title, String softwareName, String errorLog) {
    super(description, title);
    this.softwareName = softwareName;
    this.errorLog = errorLog;
  }

  public String getSoftwareName() {
    return softwareName;
  }

  public void setSoftwareName(String softwareName) {
    this.softwareName = softwareName;
  }

  public String getErrorLog() {
    return errorLog;
  }

  public void setErrorLog(String errorLog) {
    this.errorLog = errorLog;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SoftwareTicket that)) return false;
    if (!super.equals(o)) return false;
    return Objects.equals(softwareName, that.softwareName) && Objects.equals(errorLog, that.errorLog);
  }

  @Override
  public String toString() {
    return "SoftwareTicket{" +
        "status=" + this.getStatus() +
        ", ticketId='" + this.getTicketId() + '\'' +
        ", creationDate=" + this.getCreationDate() +
        ", expiryDate=" + this.getExpiryDate() +
        ", description='" + this.getDescription() + '\'' +
        ", title='" + this.getTitle() + '\'' +
        ", softwareName='" + softwareName + '\'' +
        ", errorLog='" + errorLog + '\'' +
        '}';
  }
}
