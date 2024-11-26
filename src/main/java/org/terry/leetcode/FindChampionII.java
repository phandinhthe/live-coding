package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

/**
 * <a href="https://leetcode.com/problems/find-champion-ii>Find champion II</a>
 * <p>
 * Tuition:
 * - iterate over all the edges, count the number of edges to the vertex
 * - the vertex that doesn't any edge forward to, it could be the champion
 * - If there is over 2 champions, return -1
 * <p>
 * Complexity: O(n)
 * space: O(n)
 */
public class FindChampionII {
    public static void main(String[] args) {
        new FindChampionII().run();
    }

    public void run() {
        int n;
        int[][] edges;

        n = 3;
        edges = new int[][]{{0, 1}, {1, 2}};
        Assertions.assertEquals(0, findChampion(n, edges));

        n = 4;
        edges = new int[][]{{0, 2}, {1, 3}, {1, 2}};
        Assertions.assertEquals(-1, findChampion(n, edges));

        n = 1;
        edges = new int[][]{};
        Assertions.assertEquals(0, findChampion(n, edges));

        n = 2;
        edges = new int[][]{};
        Assertions.assertEquals(-1, findChampion(n, edges));
    }

    public int findChampion(int n, int[][] edges) {
        int[] nums = new int[n];
        for (int[] edge : edges) {
            nums[edge[1]]++;
        }

        int res = -1;
        for (int v = 0; v < n; v++) {
            if (nums[v]==0) {
                if (res!=-1) return -1;
                res = v;
            }
        }

        return res;
    }

}
