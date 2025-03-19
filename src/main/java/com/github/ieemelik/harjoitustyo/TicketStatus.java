package com.github.ieemelik.harjoitustyo;

import javafx.scene.paint.Color;

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
    public Color getStatusColor() {
      return Color.RED;
    }
  },

  ON_HOLD {
    @Override
    public String getStatusText() {
      return "On hold";
    }

    @Override
    public Color getStatusColor() {
      return Color.BLUE;
    }
  },

  IN_PROGRESS {
    @Override
    public String getStatusText() {
      return "In progress";
    }

    @Override
    public Color getStatusColor() {
      return Color.YELLOW;
    }
  },

  RESOLVED {
    @Override
    public String getStatusText() {
      return "Resolved";
    }

    @Override
    public Color getStatusColor() {
      return Color.GREEN;
    }
  };

  /**
   * Gets the status text for given status value
   *
   * @return The text value associated with any status value
   */
  public String getStatusText() {
    return "";
  }

  /**
   * Gets the status color for given status value
   *
   * @return Returns the javafx Color value for given status value
   */
  public Color getStatusColor() {
    return Color.BLACK;
  }
}
