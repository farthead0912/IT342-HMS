package edu.cit.hms.configuration;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {
    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();

        byte[] key = new byte[32]; // 256-bit key

        secureRandom.nextBytes(key);

        String base64Key = Base64.getEncoder().encodeToString(key);

        System.out.println("Generated Key (Base64): " + base64Key);
    }
}
