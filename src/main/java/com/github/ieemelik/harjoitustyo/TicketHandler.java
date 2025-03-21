package com.github.ieemelik.harjoitustyo;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * This class reads and writes Ticket objects from and to a file
 *
 * @author Eemeli Koikkalainen
 * @version 1.0
 */
public class TicketHandler {
  private final ArrayList<Ticket> tickets = new ArrayList<>();
  private final String fileName = "tickets.dat";
  private final File file = new File(fileName);

  public TicketHandler() {
    if (!file.exists()) return;

    try {
      this.readFile();
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Returns all tickets as a read-only list
   *
   * @return Unmodifiable list of tickets
   */
  public List<Ticket> getTickets() {
    return Collections.unmodifiableList(this.tickets);
  }

  public String getFileName() {
    return fileName;
  }

  /**
   * Adds a ticket to the tickets list using{@link ArrayList#add(Object)}.
   *
   * @param ticket ticket to add
   */
  private void addTicket(Ticket ticket) {
    try {
      this.tickets.add(ticket);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Adds multiple tickets to the tickets list using{@link ArrayList#addAll(Collection)}
   *
   * @param tickets Collection of tickets to add
   */
  private void addTickets(Collection<Ticket> tickets) {
    try {
      this.tickets.addAll(tickets);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void readFile() throws IOException, ClassNotFoundException {
    try(FileInputStream fis = new FileInputStream(this.file);
          ObjectInputStream ois = new ObjectInputStream(fis)) {
      while (true) {
        try {
          this.tickets.add((Ticket) ois.readObject());
        } catch (EOFException e) {
          break;
        }
      }
    }
  }

  protected void writeFile() {
    try(FileOutputStream fos = new FileOutputStream(this.file);
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
      for (Ticket ticket : this.tickets) {
        oos.writeObject(ticket);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) {
    HardwareTicket ticket = new HardwareTicket("Kuvaus", "Tiketti", "Näyttö");
    SoftwareTicket ticket2 = new SoftwareTicket("Kuvaus2", "Tiketti2", "", "");

    ticket.addUpdate("Testipäivitys", TicketStatus.IN_PROGRESS);

    TicketHandler ticketHandler = new TicketHandler();
    ticketHandler.addTicket(ticket);
    ticketHandler.addTicket(ticket2);
    ticketHandler.writeFile();

    List<Ticket> tickets = ticketHandler.getTickets();
    if (!tickets.isEmpty()) {
      for (Ticket t : tickets) {
        System.out.println(t);
        System.out.println(t.getUpdates());
      }
    }
  }
}
