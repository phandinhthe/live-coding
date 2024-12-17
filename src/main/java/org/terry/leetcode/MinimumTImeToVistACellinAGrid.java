package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/minimum-time-to-visit-a-cell-in-a-grid">
 * Minimum Time to Visit a Cell In a Grid</a>
 */
public class MinimumTImeToVistACellinAGrid {
    public static void main(String[] args) {
        new MinimumTImeToVistACellinAGrid().run();
    }

    public void run() {
        //input
        int[][] grid;

        //output
        int expected;

        grid = new int[][]{{0, 1, 3, 2}, {5, 1, 2, 5}, {4, 3, 8, 6}};
        expected = 7;
        Assertions.assertEquals(expected, minimumTime(grid));

        grid = new int[][]{{0, 2, 4}, {3, 2, 1}, {1, 0, 4}};
        expected = -1;
        Assertions.assertEquals(expected, minimumTime(grid));


    }

    public int minimumTime(int[][] grid) {
        if (grid[1][0] > 1 && grid[0][1] > 1) return -1;// edge case: Cannot go to anywhere from top-left corner.
        int lastRow = grid.length - 1;
        int lastCol = grid[0].length - 1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> i1[2] - i2[2]);
        pq.offer(new int[]{0, 0, 0});
        int[][] moves = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] visit = new boolean[lastRow + 1][lastCol + 1];
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curRow = cur[0];
            int curCol = cur[1];
            int curTime = cur[2];
            if (curRow==lastRow && curCol==lastCol) {
                return curTime;
            }
            for (int[] move : moves) {
                int nextRow = curRow + move[0];
                int nextCol = curCol + move[1];
                if (nextRow < 0 || nextRow > lastRow) continue;
                if (nextCol < 0 || nextCol > lastCol) continue;
                if (visit[nextRow][nextCol]) continue;

                int diff = Math.abs(curTime - grid[nextRow][nextCol]);
                int wait = 0;
                if (diff % 2 == 0) {
                    wait = 1;
                }
                int nextTime = Math.max(curTime + 1, grid[nextRow][nextCol] + wait);
                pq.offer(new int[]{nextRow, nextCol, nextTime});
                visit[curRow][curCol] = true;
            }
        }
        return -1;
    }

}

