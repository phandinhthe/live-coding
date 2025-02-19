package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <a href='https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n'>
 * 1415. The k-th Lexicographical String of All Happy Strings of Length n
 * </a>
 * <p>
 * Tuition:
 * - generate all values of happy strings
 * - stop at the kth string
 * <p>
 * Complexity:
 * - O(2^n)
 */
public class TheKThLexicographicalStringOfAllHappyStringsOfLengthN {

    public static void main(String[] args) {
        new TheKThLexicographicalStringOfAllHappyStringsOfLengthN().run();
    }

    void run() {
        int n;
        int k;
        String actual;

        n = 1;
        k = 3;
        actual = getHappyString(1, 3);
        Assertions.assertEquals("c", actual);

        n = 3;
        k = 9;
        actual = getHappyString(n, k);
        Assertions.assertEquals("cab", actual);

        n = 1;
        k = 4;
        actual = getHappyString(n, k);
        Assertions.assertEquals("", actual);

        n = 10;
        k = 100;
        actual = getHappyString(n, k);
        Assertions.assertEquals("abacbabacb", actual);
    }

    public String getHappyString(int n, int k) {
        List<Character> happyList = new ArrayList<>(n);
        String res = "";

        for (char c : "abc".toCharArray()) {
            happyList.add(c);
            k = backtrack(happyList, n, k);
            if (k==0) {
                res = happyList.stream().map(String::valueOf).collect(Collectors.joining());
                break;
            }
            happyList.removeLast();
        }
        return res;
    }

    int backtrack(List<Character> happyList, int n, int k) {
        if (happyList.size()==n) {
            return k - 1;
        }
        char last = happyList.getLast();
        for (char c : "abc".toCharArray()) {
            if (c==last) continue;

            happyList.add(c);
            k = backtrack(happyList, n, k);
            if (k==0) return 0;
            happyList.removeLast();
        }
        return k;
    }

}
