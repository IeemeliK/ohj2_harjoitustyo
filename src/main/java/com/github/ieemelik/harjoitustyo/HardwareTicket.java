package com.github.ieemelik.harjoitustyo;

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
}
