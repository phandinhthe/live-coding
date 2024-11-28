package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner">
 * 2290. Minimum Obstacle Removal to Reach Corner
 * </a>
 * <p>
 * Tuition:
 * - mark 2 all possible cell that could lead to bot-right corner.
 * - apply Djikstra until finding out the first cell 2, that is marked above.
 * - - recalculate the weight to every cell
 * - - update every weight of the visited cell to cache
 * - - Just go with the cell that has weight is lesser than in cache
 * <p>
 * Complexity: O(m*n)
 */
public class MinimumObstacleRemovalToReachCorner {
    public static void main(String[] args) {
        new MinimumObstacleRemovalToReachCorner().run();
    }

    public void run() {
        // input
        int[][] grid;

        //ouput
        int expected;

        grid = new int[][]{{0, 1, 1}, {1, 1, 0}, {1, 1, 0}};
        expected = 2;
        Assertions.assertEquals(expected, minimumObstacles(grid));

        grid = new int[][]{{0, 1, 0, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 0, 1, 0}};
        expected = 0;
        Assertions.assertEquals(expected, minimumObstacles(grid));

        grid = new int[][]{{0, 0, 1, 1, 1, 1, 0, 0, 0, 1}, {0, 1, 1, 1, 1, 1, 1, 0, 1, 1}, {1, 1, 0, 1, 1, 1, 1, 0, 1, 0}, {0, 0, 1, 1, 1, 1, 0, 0, 1, 1}, {1, 0, 1, 0, 0, 0, 1, 1, 1, 0}};
        expected = 5;
        Assertions.assertEquals(expected, minimumObstacles(grid));
    }

    public int minimumObstacles(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        visit = new boolean[rows][cols];
        cache = new int[rows][cols];
        for (int[] c : cache) {
            Arrays.fill(c, rows * cols);
        }
        grid[rows - 1][cols - 1] = 2;
        markAllPossibleCells(grid, new int[]{rows - 1, cols - 1});
        return djikstra(grid);
    }

    int[][] moves = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean[][] visit;
    int[][] cache;

    public int djikstra(int[][] grid) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((c1, c2) -> cache[c1[0]][c1[1]] - cache[c2[0]][c2[1]]);
        cache[0][0] = 0;
        pq.offer(new int[]{0, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curRow = cur[0];
            int curCol = cur[1];
            if (grid[curRow][curCol]==2) return cache[curRow][curCol];
            visit[curRow][curCol] = true;

            for (int[] move : moves) {
                int nextRow = curRow + move[0];
                int nextCol = curCol + move[1];
                if (nextRow < 0 || nextRow > grid.length - 1) continue;
                if (nextCol < 0 || nextCol > grid[0].length - 1) continue;
                if (visit[nextRow][nextCol]) continue;

                int increment = grid[curRow][curCol]==1 ? 1:0;
                int newObstacle = cache[curRow][curCol] + increment;
                if (newObstacle < cache[nextRow][nextCol]) {
                    cache[nextRow][nextCol] = newObstacle;
                    pq.offer(new int[]{nextRow, nextCol});
                }
            }
        }
        return -1;
    }

    public void markAllPossibleCells(int[][] grid, int[] cur) {
        int curRow = cur[0];
        int curCol = cur[1];
        if (curRow < 0 || curRow > grid.length - 1) return;
        if (curCol < 0 || curCol > grid[0].length - 1) return;
        if (grid[curRow][curCol]==0) grid[curRow][curCol] = 2;
        else return;
        for (int[] move : moves) {
            int[] next = new int[]{cur[0] + move[0], cur[1] + move[1]};
            markAllPossibleCells(grid, next);
        }
    }

}
