package edu.dbms.joinsociety.helper;

import lombok.SneakyThrows;

import java.security.MessageDigest;

public class PasswordHelper {

    @SneakyThrows
    public static String hashPasswordText(final String password) {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(password.getBytes());

        return bytesToHex(encodedHash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
