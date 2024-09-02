package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

/**
 * Tuition:
 * - remove all the duplicates in sequences.
 * - calculate the turns to print `turns = solve(start, end) = 1 + solve(start + 1, end)`
 * - if there is an `i` existing so that s[i] == s[start]: turns = min(turns, solve(start + 1, mid) + solve(mid+1, end));
 * - Use cache[][] to cache the min result.
 */
public class StrangePrinter {
    public static void main(String[] args) {
        new StrangePrinter().run();
    }

    private void run() {
        String s;
        int output;

        s = "aaabbb";
        output = 2;
        Assertions.assertEquals(output, strangePrinter(s));

        s = "aba";
        output = 2;
        Assertions.assertEquals(output, strangePrinter(s));

        s = "abcabc";
        output = 5;
        Assertions.assertEquals(output, strangePrinter(s));

        s = "leetcode";
        output = 6;
        Assertions.assertEquals(output, strangePrinter(s));

        s = "caaabdbbcbccdbcbcdcccabdcdadbccaaaddaaccbadddabca";
        output = 20;
        Assertions.assertEquals(output, strangePrinter(s));
    }

    public int strangePrinter(String s) {
        StringBuilder sb = new StringBuilder();
        char prev = '\b';
        for (char c : s.toCharArray()) {
            if (c==prev) continue;
            sb.append(c);
            prev = c;
        }
        s = sb.toString();
        int[][] cache = new int[s.length()][s.length()];
        return solve(s.toCharArray(), cache, 0, s.length());
    }

    private int solve(char[] arr, int[][] cache, int start, int end) {
        if (start==end) return 0;
        if (cache[start][end - 1]!=0) return cache[start][end - 1];
        int ans = 1 + solve(arr, cache, start + 1, end);
        for (int mid = start + 1; mid < end; mid++) {
            if (arr[mid]!=arr[start]) continue;
            ans = Math.min(ans, solve(arr, cache, start + 1, mid) + solve(arr, cache, mid, end));
        }
        cache[start][end - 1] = ans;
        return ans;
    }

}
