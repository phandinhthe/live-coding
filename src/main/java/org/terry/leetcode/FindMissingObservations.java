package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.stream.IntStream;

/**
 * <a href="https://leetcode.com/problems/find-missing-observations">Leetcode 2028: Find missing observations</a>
 * <p>
 * tuition:
 * - mean*(m + n) = total, find_total = total - sum(rolls).
 * - res[n]
 * - if (find_total < 0 || find_total > 6 * n || find_total < n) return empty
 * - else while(find_total > 6) :i = (i ++)%n => res[i] ++;
 * <p>
 * Complexity: O(n*6)
 * Space: O(n)
 */
public class FindMissingObservations {
    public static void main(String[] args) {

    }

    public void run() {
        // input
        int[] rolls;
        int mean, n;
        // assertion

        rolls = new int[]{3, 2, 4, 3};
        mean = 4;
        n = 2;
        assertTest(new int[]{6, 6}, missingRolls(rolls, mean, n));

        rolls = new int[]{1, 5, 6};
        mean = 3;
        n = 4;
        assertTest(new int[]{2, 3, 2, 2}, missingRolls(rolls, mean, n));

        rolls = new int[]{1, 2, 3, 4};
        mean = 6;
        n = 4;
        assertTest(new int[]{}, missingRolls(rolls, mean, n));
    }

    void assertTest(int[] output, int[] actual) {
        if (output.length==0 && actual.length==0) return;
        IntStream.of(output).filter(i -> i > 6).findFirst().ifPresent((i) -> {
            throw new RuntimeException("value cannot greater than 6");
        });
        Assertions.assertEquals(IntStream.of(output).sum(), IntStream.of(actual).sum());
    }

    public int[] missingRolls(int[] rolls, int mean, int n) {
        int total = (rolls.length + n) * mean;
        total -= IntStream.of(rolls).sum();
        int[] res = new int[n];

        if (total < 0 || res.length > total || res.length * 6 < total) return new int[]{};
        int i = 0;
        while (total > 0) {
            res[i] += 1;
            total--;
            i = (i + 1) % res.length;
        }
        return res;
    }
}
