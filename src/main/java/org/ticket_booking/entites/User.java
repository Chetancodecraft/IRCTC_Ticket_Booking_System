package org.ticket_booking.entites;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String password;
    private String hashPassword;
    private List<Ticket> ticketBooked;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.ticketBooked = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public List<Ticket> getTicketBooked() {
        return ticketBooked;
    }

    public void addTicket(Ticket ticket) {
        this.ticketBooked.add(ticket);
    }

    public void printTickets() {
        if (ticketBooked.isEmpty()) {
            System.out.println("No tickets booked.");
        } else {
            System.out.println("Booked Tickets:");
            for (Ticket ticket : ticketBooked) {
                System.out.println(ticket);
            }
        }
    }
}
