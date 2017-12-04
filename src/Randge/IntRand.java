package Randge;

import java.util.Random;

public class IntRand {
    private int begin, end;
    Random ran;

    public IntRand() {
        this.begin = 0;
        this.end = Integer.MAX_VALUE;
        ran = new Random();
    }

    public IntRand(int begin, int end) {
        this.begin = begin;
        this.end = end;
        ran = new Random();
    }

    public int getInt() {
        return ran.nextInt(this.end - this.begin) + this.begin;
    }

    public int getInt(int begin, int end) {
        return ran.nextInt(end - begin) + begin;
    }

}
