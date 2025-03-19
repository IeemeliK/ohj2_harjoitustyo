package com.github.ieemelik.harjoitustyo;

import java.util.Objects;

/**
 *
 */
public class HardwareTicket extends Ticket {
  private String deviceType;

  HardwareTicket(String description, String title, String deviceType) {
    super(description, title);
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
        ", expiryDate=" + this.getExpiryDate() +
        ", description='" + this.getDescription() + '\'' +
        ", title='" + this.getTitle() + '\'' +
        ", deviceType='" + deviceType + '\'' +
        '}';
  }
}
