package org.ticket_booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ticket_booking.entites.User;
import org.ticket_booking.utils.userServiceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class userBookingService {
    private List<User> userList;
    private static final String userPath = "src/main/resources/users.json"; // Updated for Maven
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public userBookingService() throws IOException {
        File users = new File(userPath);
        if (users.exists()) {
            userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
        } else {
            userList = new ArrayList<>();
        }
    }

    public boolean loginUser(User user) {
        Optional<User> foundUser = userList.stream()
                .filter(user1 -> user1.getName().equalsIgnoreCase(user.getName()) &&
                        userServiceUtils.checkPassword(user.getPassword(), user1.getHashPassword()))
                .findFirst();

        return foundUser.isPresent();
    }

    public boolean signUp(User user1) {
        try {
            user1.setHashPassword(userServiceUtils.hashPassword(user1.getPassword()));
            userList.add(user1);
            saveUserListToFile();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    private void saveUserListToFile() throws IOException {
        objectMapper.writeValue(new File(userPath), userList);
    }

    public void fetchBooking(User user) {
        user.printTickets();
    }

    public boolean cancelBooking(String ticketId, User user) {
        boolean removed = user.getTicketBooked().removeIf(ticket -> ticket.getTicketId().equals(ticketId));
        if (removed) {
            try {
                saveUserListToFile();
            } catch (IOException e) {
                return false;
            }
        }
        return removed;
    }

    public void viewBookings(User user) {
        if (user.getTicketBooked().isEmpty()) {
            System.out.println(" No bookings found.");
        } else {
            System.out.println("Your Bookings:");
            user.getTicketBooked().forEach(System.out::println);
        }
    }
}
