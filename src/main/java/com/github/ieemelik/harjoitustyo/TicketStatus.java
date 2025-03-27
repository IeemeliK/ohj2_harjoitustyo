package com.github.ieemelik.harjoitustyo;

/**
 * Values to set the status of each Ticket-object. Each enum constant also implements getStatusText- and
 * getStatusColor-methods.
 */
public enum TicketStatus {
  NEW("Uusi", "red"),
  IN_PROGRESS("Kesken", "yellow"),
  ON_HOLD("Pidossa", "lightblue"),
  RESOLVED("Ratkaistu", "lightgreen");

  private final String statusText;
  private final String statusColor;

  TicketStatus(String statusText, String statusColor) {
    this.statusText = statusText;
    this.statusColor = statusColor;
  }

  /**
   * Gets the status text for calling status value
   *
   * @return The text value associated with any status value
   */
  public String getStatusText() {
    return this.statusText;
  }

  /**
   * Gets the status color for calling status value
   *
   * @return Returns the color value as string for given status value
   */
  public String getStatusColor() {
    return this.statusColor;
  }
}
