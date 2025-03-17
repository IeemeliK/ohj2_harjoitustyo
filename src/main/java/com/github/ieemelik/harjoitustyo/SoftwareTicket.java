package com.github.ieemelik.harjoitustyo;

public class SoftwareTicket extends Ticket {
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
}
