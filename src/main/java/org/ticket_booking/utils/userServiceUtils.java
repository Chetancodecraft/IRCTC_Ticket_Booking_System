package org.ticket_booking.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class userServiceUtils {

    /**
     * Hashes a password using SHA-256.
     *
     * @param password The plain text password.
     * @return The hashed password.
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    /**
     * Checks if the entered password matches the stored hashed password.
     *
     * @param enteredPassword The plain text password entered by the user.
     * @param storedHash The hashed password stored in the database.
     * @return True if passwords match, otherwise false.
     */
    public static boolean checkPassword(String enteredPassword, String storedHash) {
        return hashPassword(enteredPassword).equals(storedHash);
    }
}
