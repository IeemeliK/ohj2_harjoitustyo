package com.github.ieemelik.harjoitustyo;

/**
 * Values to set the status of each Ticket-object. Each enum constant also implements getStatusText- and
 * getStatusColor-methods.
 *
 * @see TicketStatus#getStatusColor()
 * @see TicketStatus#getStatusText()
 */
public enum TicketStatus {
  NEW {
    @Override
    public String getStatusText() {
      return "To do";
    }

    @Override
    public String getStatusColor() {
      return "red";
    }
  },

  IN_PROGRESS {
    @Override
    public String getStatusText() {
      return "In progress";
    }

    @Override
    public String getStatusColor() {
      return "yellow";
    }
  },

  ON_HOLD {
    @Override
    public String getStatusText() {
      return "On hold";
    }

    @Override
    public String getStatusColor() {
      return "lightblue";
    }
  },

  RESOLVED {
    @Override
    public String getStatusText() {
      return "Resolved";
    }

    @Override
    public String getStatusColor() {
      return "green";
    }
  };

  /**
   * Gets the status text for calling status value
   *
   * @return The text value associated with any status value
   */
  public String getStatusText() {
    return "";
  }

  /**
   * Gets the status color for calling status value
   *
   * @return Returns the color value as string for given status value
   */
  public String getStatusColor() {
    return "black";
  }
}
