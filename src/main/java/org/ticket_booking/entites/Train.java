package org.ticket_booking.entites;

import java.util.List;
import java.util.Map;

public class Train {
    private String trainId;
    private String trainNumber;
    private List<String> route;
    private Map<String, Integer> seatAvailability;
    private List<Ticket> bookedTickets;
    private int seatsAvailable;
    private String source; // ✅ Add Source
    private String destination; 



    public Train() {}
    public Train(String trainId, String trainNumber, List<String> route, 
                 Map<String, Integer> seatAvailability, List<Ticket> bookedTickets,
                 int seatsAvailable, String source, String destination) {
        this.trainId = trainId;
        this.trainNumber = trainNumber;
        this.route = route;
        this.seatAvailability = seatAvailability;
        this.bookedTickets = bookedTickets;
        this.seatsAvailable = seatsAvailable;
        this.source = source;
        this.destination = destination;
    }

   
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainId='" + trainId + '\'' +
                ", trainNumber='" + trainNumber + '\'' +
                ", route=" + route +
                ", seatsAvailable=" + seatsAvailable +
                ", seatAvailability=" + seatAvailability +
                ", bookedTickets=" + bookedTickets +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }

  
    public String getTrainNumber() {
        return trainNumber;  
    }
    
   
    public int getSeatsAvailable() {
        return seatsAvailable;  
    }
    
}

