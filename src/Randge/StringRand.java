package Randge;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class StringRand {
    private int length;

    public StringRand(int length) {
        this.length = length;
    }

    public String getString() {
        StringBuilder builder = new StringBuilder(this.length);
        for (int i = 0; i < this.length; i++) {
            builder.append((char) (ThreadLocalRandom.current().nextInt(33, 128)));
        }
        return builder.toString();
    }

    public static String RandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(62);
            buf.append(str.charAt(num));
        }
        return buf.toString();
    }


}
