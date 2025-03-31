package com.github.ieemelik.harjoitustyo;

import java.io.Serializable;
import java.util.Objects;

/**
 * A class for hardware related tickets
 *
 * @author Eemeli Koikkalainen
 * @version 1.0
 */
public class HardwareTicket extends Ticket implements Serializable {
  private String deviceType;

  HardwareTicket(String title, String description){
    super(title, description);
  }

  HardwareTicket(String title, String description, String deviceType) {
    super(title, description);
    this.deviceType = deviceType;
  }

  public String getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof HardwareTicket that)) return false;
    if (!super.equals(o)) return false;
    return Objects.equals(deviceType, that.deviceType);
  }

  @Override
  public String toString() {
    return "HardwareTicket{" +
        "status=" + this.getStatus() +
        ", ticketId='" + this.getTicketId() + '\'' +
        ", creationDate=" + this.getCreationDate() +
        ", description='" + this.getDescription() + '\'' +
        ", title='" + this.getTitle() + '\'' +
        ", deviceType='" + deviceType + '\'' +
        '}';
  }
}
