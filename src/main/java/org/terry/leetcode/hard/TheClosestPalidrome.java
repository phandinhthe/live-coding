package org.terry.leetcode.hard;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-the-closest-palindrome/description/?envType=daily-question&envId=2024-08-24
 * Leetcode 564: Find the closest palindrom
 * Tuition:
 * - find the left of `n` with half length of input `n`
 * - buid the palindrom from the `left`. // when left, left - 1, left + 1
 * - check 2 more cases:
 * - - Math.pow(10, n.length-1) - 1// n = 10001 => 9999,
 * - - Math.pow(10, n.length) + 1 // n = 99999 => 1_000_001
 * <p>
 * Complexity: O(n)
 */

public class TheClosestPalidrome {
    public static void main(String[] args) {
        new TheClosestPalidrome().test();
    }

    public void test() {
        String input;
        String expected;
        String output;

        input = "9009";
        expected = "8998";
        output = nearestPalindromic(input);
        Assertions.assertEquals(expected, output);

        input = "100";
        expected = "99";
        output = nearestPalindromic(input);
        Assertions.assertEquals(expected, output);

        input = "9";
        expected = "8";
        output = nearestPalindromic(input);
        Assertions.assertEquals(expected, output);

        input = "123";
        expected = "121";
        output = nearestPalindromic(input);
        Assertions.assertEquals(expected, output);

        input = "1";
        expected = "0";
        output = nearestPalindromic(input);
        Assertions.assertEquals(expected, output);

        input = "10";
        expected = "9";
        output = nearestPalindromic(input);
        Assertions.assertEquals(expected, output);

        input = "1234";
        expected = "1221";
        output = nearestPalindromic(input);
        Assertions.assertEquals(expected, output);

        input = "4321";
        expected = "4334";
        output = nearestPalindromic(input);
        Assertions.assertEquals(expected, output);
    }

    public String nearestPalindromic(String n) {

        boolean isEven = (n.length() % 2) == 0;
        long left = Long.parseLong(n.substring(0, (n.length() + 1) / 2));

        List<Long> list = new ArrayList<>();
        list.add(palindrome(left, isEven));
        list.add(palindrome(left + 1, isEven));
        list.add(palindrome(left - 1, isEven));

        list.add((long) (Math.pow(10, n.length() - 1) - 1));
        list.add((long) (Math.pow(10, n.length()) + 1));

        long cur = Long.parseLong(n);
        long diff = Long.MAX_VALUE;
        long res = Long.MAX_VALUE;
        for (long value : list) {
            if (cur == value) continue;
            if (Math.abs(cur - value) < diff) {
                res = value;
                diff = Math.abs(cur - value);
            } else if (Math.abs(cur - value) == diff) {
                res = Math.min(res, value);
            }
        }
        return res + "";
    }

    public long palindrome(long left, boolean isEven) {
        long num = left;
        if (!isEven) num /= 10;
        while (num != 0) {
            left = left * 10 + num % 10;
            num /= 10;
        }

        return left;
    }
}
