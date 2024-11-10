package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Locale;

/**
 * <a href="https://leetcode.com/problems/minimum-array-end/">
 * Minimum Array End
 * </a>
 */
public class MinimumArrayEnd {
    public static void main(String[] args) {
        new MinimumArrayEnd().run();
    }

    public void run() {
        // input
        int n;
        int x;
        int expected;

        n = 2;
        x = 1;
        expected = 3;
        Assertions.assertEquals(expected, minEnd(n, x));

        n = 1;
        x = 1;
        expected = 1;
        Assertions.assertEquals(expected, minEnd(n, x));

        n = 3;
        x = 4;
        expected = 6;
        Assertions.assertEquals(expected, minEnd(n, x));

        n = 2;
        x = 7;
        expected = 15;
        Assertions.assertEquals(expected, minEnd(n, x));

        n = 3;
        x = 4;
        expected = 6;
        Assertions.assertEquals(expected, minEnd(n, x));

    }

    public long minEnd(int n, int x) {
        long rs = x;
        long ix = 1;
        long in = 1;

        while (in <= n - 1) {
            if ((ix & x)==0L) {
                if (((n - 1) & in)!=0L) {
                    rs = rs | ix;
                }
                in = in << 1;
            }
            ix = ix << 1;
        }
        return rs;
    }
}
