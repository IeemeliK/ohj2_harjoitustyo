package com.github.ieemelik.harjoitustyo;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

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

  /**
   * Attempts to read ticket-objects from "tickets.dat" file. If the file does not exist returns with an empty tickets
   * list.
   */
  public TicketHandler() {
    /*
     * DUMMY DATA FOR TESTING PURPOSES
     * TODO: REMOVE LATER
     */
    if (!file.exists()) {
      HardwareTicket ticket = new HardwareTicket("Näyttö ei toimi", "Näyttö särki", "Näyttö");
      SoftwareTicket ticket2 = new SoftwareTicket("Kuvaus2", "Word kirjoittaa itsestään", "", "");
      HardwareTicket ticket3 = new HardwareTicket("Hiiri särki", "Hiiri hajonnut", "Hiiri");
      SoftwareTicket ticket4 = new SoftwareTicket("Exceli ei aukea", "Exceli särki", "Microsoft Excel", "");

      Ticket[] dummyTickets = new Ticket[]{ticket, ticket2, ticket3, ticket4};

      ticket.addUpdate("Näyttö laitettu päälle", TicketStatus.RESOLVED);
      ticket4.addUpdate("Tarkasteltu ongelmaa etäyhteydellä", TicketStatus.IN_PROGRESS);
      ticket2.setStatus(TicketStatus.ON_HOLD);

      this.addTickets(List.of(dummyTickets));
    } else {
      try {
        this.readFile();
      } catch (IOException | ClassNotFoundException e) {
        throw new RuntimeException(e);
      }
    }

    // Sorts tickets by status
    this.tickets.sort(Comparator.comparing(Ticket::getStatus));
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
  protected void addTicket(Ticket ticket) {
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
  protected void addTickets(Collection<Ticket> tickets) {
    try {
      this.tickets.addAll(tickets);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  protected boolean removeTicket(Ticket ticket) {
    try {
      return this.tickets.remove(ticket);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Reads ticket-objects from file into this.tickets
   *
   * @throws IOException
   * @throws ClassNotFoundException
   */
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

  /**
   * Writes the contents of this.tickets to a file
   */
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
}
