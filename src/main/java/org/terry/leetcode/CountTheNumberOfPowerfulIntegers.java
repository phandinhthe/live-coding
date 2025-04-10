package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

/**
 * <a href="https://leetcode.com/problems/count-the-number-of-powerful-integers/">
 * 2999. Count the number of powerful integers
 * </a>
 */
public class CountTheNumberOfPowerfulIntegers {
    public static void main(String[] args) {
        new CountTheNumberOfPowerfulIntegers().run();
    }

    public void run() {
        long start, finish;
        int limit;
        String s;

        start = 1;
        finish = 6000;
        limit = 4;
        s = "124";
        Assertions.assertEquals(5, numberOfPowerfulInt(start, finish, limit, s));

        start = 15;
        finish = 215;
        limit = 6;
        s = "10";
        Assertions.assertEquals(2, numberOfPowerfulInt(start, finish, limit, s));

        start = 1;
        finish = 2000;
        limit = 4;
        s = "123";
        Assertions.assertEquals(2, numberOfPowerfulInt(start, finish, limit, s));
    }

    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        return calculate(String.valueOf(finish), limit, s) -
            calculate(String.valueOf(start - 1), limit, s);
    }

    public long calculate(String x, int limit, String s) {
        int prelen = x.length() - s.length();

        if (prelen < 0) return 0;
        if (prelen == 0) {
            return x.compareTo(s) >= 0 ? 1 : 0;
        }

        char[] arr = x.toCharArray();
        long cnt = 0L;
        for (int i = 0; i < prelen; i++) {
            int digit = arr[i] - '0';

            // case digit > limit, all values in [i..prelen] could be limit+1
            if (digit > limit) {
                cnt += (long) Math.pow(limit + 1, prelen - i);
                return cnt;
            }

            // case digit <= limit, cnt +=  digit * (limit + 1) ^ prelen-i-1;
            // [i, j...prelen-1], j = i + 1
            // =>   digit * (limit+1) ^ (prelen-1-j+1)
            // =    digit * (limit + 1) ^ (prelen-i-1)
            cnt += (digit * (long) Math.pow(limit + 1, prelen - i - 1));
        }

        if (x.substring(prelen).compareTo(s) >= 0) {
            cnt++;
        }

        return cnt;
    }
}
