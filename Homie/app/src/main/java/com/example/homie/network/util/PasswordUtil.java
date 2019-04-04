package com.example.homie.network.util;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Utility class used to generate password salt and hash the password.
 */
public final class PasswordUtil {

    /**
     * Generates a byte array containing the salt that will be used in password hashing.
     *
     * @return the generated salt
     */
    public static byte[] generateSalt() {
        byte[] salt = new byte[20];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);

        return salt;
    }

    /**
     * Generates a password hash from a character array and salt.
     * The iterations determine the number of times the underlying encryption algorithm will run, the higher the
     * number the slower the algorithm.
     * Key length of 256 is considered secure.
     *
     * @param password password to be hashed
     * @param salt salt to be used in hashing of the password
     * @param iterations the number of times to run the encryption algorithm
     * @param keyLength length of the key
     * @return the hashed password
     */
    public static byte[] hashPassword(final char[] password, final byte[] salt, final int iterations, final int keyLength) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded();

            return res;

        } catch(NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

}
