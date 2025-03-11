package org.ticket_booking;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.ticket_booking.entites.Train;
import org.ticket_booking.entites.User;
import org.ticket_booking.services.trainServices;
import org.ticket_booking.services.userBookingService;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        userBookingService bookingService;
        trainServices trainService;

        try {
            bookingService = new userBookingService();
            trainService = new trainServices();
        } catch (IOException e) {
            System.out.println("Error initializing services: " + e.getMessage());
            return;
        }

        User currentUser = null;

        while (true) {
            System.out.println("\n--- IRCTC Train Ticket Booking System ---");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. View Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid option.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    User newUser = new User(name, password);

                    if (bookingService.signUp(newUser)) {
                        System.out.println("Sign-up successful!");
                    } else {
                        System.out.println("Sign-up failed! Username might be taken.");
                    }
                    break;

                case 2:
                    System.out.print("Enter name: ");
                    String loginName = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    User loginUser = new User(loginName, loginPassword);

                    if (bookingService.loginUser(loginUser)) {
                        System.out.println("Login successful!");
                        currentUser = loginUser;
                    } else {
                        System.out.println("Invalid credentials! Try again.");
                    }
                    break;

                case 3:
                    if (currentUser != null) {
                        bookingService.viewBookings(currentUser);
                    } else {
                        System.out.println("You need to log in first!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Source Station: ");
                    String source = scanner.nextLine();
                    System.out.print("Enter Destination Station: ");
                    String destination = scanner.nextLine();

                    List<Train> availableTrains = trainService.viewAvailableTrains(source, destination);
                    if (availableTrains.isEmpty()) {
                        System.out.println("No trains available for the given route.");
                    } else {
                        System.out.println("\nAvailable Trains:");
                        for (Train train : availableTrains) {
                            System.out.println("Train No: " + train.getTrainNumber() + " | Destination: " + train.getDestination() + " | Seats Available: " + train.getSeatsAvailable());
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}