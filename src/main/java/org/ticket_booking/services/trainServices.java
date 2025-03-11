package org.ticket_booking.services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.ticket_booking.entites.Train;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class trainServices {
    private static final String trainPath ="src\\main\\java\\org\\ticket_booking\\localdb\\train.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private List<Train> trainList;

    public trainServices() throws IOException {
        File trainFile = new File(trainPath);
        if (trainFile.exists()) {
            trainList = objectMapper.readValue(trainFile, new TypeReference<List<Train>>() {});
        } else {
            throw new IOException(" Train data file not found!");
        }
    }

    
    public List<Train> viewAvailableTrains(String source, String destination) {
        return trainList.stream()
                .filter(train -> train.getSource().equalsIgnoreCase(source) && train.getDestination().equalsIgnoreCase(destination)) // ✅ Now `getSource()` exists
                .collect(Collectors.toList());
    }

    public boolean checkSeatAvailability(String trainNumber, int seatsRequested) {
        return trainList.stream()
                .filter(train -> train.getTrainNumber().equals(trainNumber))
                .anyMatch(train -> train.getSeatsAvailable() >= seatsRequested);
    }


    public Train getTrainByNumber(String trainNumber) {
        return trainList.stream()
                .filter(train -> train.getTrainNumber().equals(trainNumber))
                .findFirst()
                .orElse(null);
    }
}
