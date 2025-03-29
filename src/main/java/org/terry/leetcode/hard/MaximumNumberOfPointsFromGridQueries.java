package org.terry.leetcode.hard;

import org.junit.jupiter.api.Assertions;

import java.util.*;


/**
 * <a href="https://leetcode.com/problems/maximum-number-of-points-from-grid-queries/?envType=daily-question&envId=2025-03-28">
 * 2503. Maximum Number of Points from Grid
 * </a>
 */
public class MaximumNumberOfPointsFromGridQueries {
    public static void main(String[] args) {
        new MaximumNumberOfPointsFromGridQueries().run();
    }

    private void run() {
        // input
        int[][] grid;
        int[] queries;

        grid = new int[][]{{1, 2, 3}, {2, 5, 7}, {3, 5, 1}};
        queries = new int[]{5, 6, 2};
        Assertions.assertArrayEquals(new int[]{5, 8, 1}, maxPoints(grid, queries));

        grid = new int[][]{{5,2,1},{1,1,2}};
        queries = new int[]{3};
        Assertions.assertArrayEquals(new int[]{0}, maxPoints(grid, queries));
    }

    public int[] maxPoints(int[][] grid, int[] queries) {
        int[] res = new int[queries.length];

        List<int[]> queryIndex = new ArrayList<>(queries.length);
        for (int i = 0; i < queries.length; i++) {
            queryIndex.add(new int[]{i, queries[i]});
        }
        queryIndex.sort(Comparator.comparingInt(value -> value[1]));

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(value -> grid[value[0]][value[1]]));
        pq.offer(new int[] {0, 0});
        visited[0][0] = true;
        int curPoint = 0;
        int point = 0;
        for (int[] query : queryIndex) {
            curPoint = travel(grid, query[1], pq, visited);
            point += curPoint;
            res[query[0]] = point;
        }
        return res;
    }

    int[][] directions = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}};

    int travel(int[][] grid, int query, PriorityQueue<int[]> pq, boolean[][] visited) {
        int res = 0;
        while(!pq.isEmpty()) {
            // peek cur point and check valid
            int[] cur = pq.peek();
            int x = cur[0];
            int y = cur[1];
            if (query <= grid[x][y]) break;

            //poll the valid cur point and increment res
            res ++;
            pq.poll();

            // iterate the neighbors
            for (int[] d : directions) {
                int neix = x + d[0];
                int neiy= y + d[1];

                // check valid nei
                if (neix < 0 || neix >= grid.length || neiy < 0 || neiy >= grid[0].length) continue;
                if (visited[neix][neiy]) continue;

                visited[neix][neiy] = true;
                pq.offer(new int[]{neix, neiy});
            }
        }

        return res;
    }
}