package braces.util;

import java.util.Random;

/**
 *
 * @author atuandev
 */
public class RandomGenerator {
    public static String getRandomId() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 9;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
    public static String getRandomNumber() {
        String alphabet = "0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 9;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
