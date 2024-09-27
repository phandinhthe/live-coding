package org.terry.leetcode.hard;

import org.junit.jupiter.api.Assertions;

/**
 * <a href="https://leetcode.com/problems/k-th-smallest-in-lexicographical-order">K-th Smallest in Lexicographical Order</a>
 * <p>
 * tuition:
 * - Count the lexicographical-ordered numbers from (n)
 * -- if count <= k, cur += 1, i += count
 * -- else cur *= 10, i ++;
 * -- end when i == k, return cur
 * <p>
 * Complexity: O(logn*logn)
 * Space: O(1)
 */

public class KthSmallestInLexicographicalOrder {
    public static void main(String[] args) {
        new KthSmallestInLexicographicalOrder().run();
    }

    void run() {
        int n, k;
        int actual, expected;

        n = 13;
        k = 2;
        expected = 10;
        actual = findKthNumber(n, k);
        Assertions.assertEquals(expected, actual);

        n = 1;
        k = 1;
        expected = 1;
        actual = findKthNumber(n, k);
        Assertions.assertEquals(expected, actual);

        n = 1_000;
        k = 1_000;
        expected = 999;
        actual = findKthNumber(n, k);
        Assertions.assertEquals(expected, actual);

        n = 1_000_000;
        k = 1_000;
        expected = 100896;
        actual = findKthNumber(n, k);
        Assertions.assertEquals(expected, actual);

        n = 1_000_000_000;
        k = 1000;
        expected = 100000893;
        actual = findKthNumber(n, k);
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected, actual);

        n = 681692778;
        k = 351251360;
        expected = 416126219;
        actual = findKthNumber(n, k);
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected, actual);
    }

    public int findKthNumber(int n, int k) {
        int i = 1;
        int cur = 1;

        while (i < k) {
            int cnt = count(cur, n);
            if (i + cnt <= k) {
                i += cnt;
                cur++;
            } else {
                cur *= 10;
                i++;
            }
        }

        return cur;
    }

    /**
     * Count the lexi-ordered number
     *
     * @param cur   current value
     * @param limit n
     * @return return the number of lexi-ordered counted from `current value`
     */
    private int count(long cur, long limit) {
        int cnt = 0;
        long nei = cur + 1;
        while (cur <= limit) {
            cnt += (int) (Math.min(nei, limit + 1) - cur);
            cur *= 10;
            nei *= 10;
        }
        return cnt;
    }
}
