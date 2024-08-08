package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/filling-bookcase-shelves/
 * <p>
 * tuition:
 * - backtrack
 * - cache
 *
 * Complexity:  O(n^2)
 * Space:       O(n)
 */
public class FillingBookcaseShelves {
    public static void main(String[] args) {
        new FillingBookcaseShelves().test();
    }


    public void test() {
        int[][] books;
        int shelfWidth;
        int expected, output;

        books = new int[][]{{1, 1}, {2, 3}, {2, 3}, {1, 1}, {1, 1}, {1, 1}, {1, 2}};
        shelfWidth = 4;
        expected = 6;
        output = minHeightShelves(books, shelfWidth);
        Assertions.assertEquals(expected, output);
    }

    int[] cache;

    public int minHeightShelves(int[][] books, int shelfWidth) {
        cache = new int[books.length];
        Arrays.fill(cache, -1);
        return backtrack(books, 0, shelfWidth);
    }

    int backtrack(int[][] books, int cur, int selfWidth) {
        if (cur == books.length) {
            return 0;
        }
        if (cache[cur] != -1) return cache[cur];
        int maxHeight = 0;
        int remain = selfWidth;
        int result = Integer.MAX_VALUE;
        for (int next = cur; next < books.length; next++) {
            int width = books[next][0];
            int height = books[next][1];
            if (width > remain) {
                break;
            }

            remain -= width;
            maxHeight = Math.max(height, maxHeight);
            result = Math.min(result, maxHeight + backtrack(books, next + 1, selfWidth));
        }
        cache[cur] = result;
        return result;
    }


}
