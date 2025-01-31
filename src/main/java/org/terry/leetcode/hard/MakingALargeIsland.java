package org.terry.leetcode.hard;

import org.junit.jupiter.api.Assertions;

/**
 * <a href="https://leetcode.com/problems/making-a-large-island">Making a large island</a>
 */
public class MakingALargeIsland {
    public static void main(String[] args) {
        new MakingALargeIsland().run();
    }

    private void run() {
        int[][] grid;
        grid = new int[][]{{1, 0}, {0, 1}};
        Assertions.assertEquals(3, largestIsland(grid));

        grid = new int[][]{{1, 1}, {0, 1}};
        Assertions.assertEquals(4, largestIsland(grid));

        grid = new int[][]{{1, 1}, {1, 1}};
        Assertions.assertEquals(4, largestIsland(grid));

        /**
         * [[0,0,0,0,0,0,0],
         * [0,1,1,1,1,0,0],
         * [0,1,0,0,1,0,0],
         * [1,0,1,0,1,0,0],
         * [0,1,0,0,1,0,0],
         * [0,1,0,0,1,0,0],
         * [0,1,1,1,1,0,0]]
         */
        grid = new int[][]{
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 0, 0},
            {0, 1, 0, 0, 1, 0, 0},
            {1, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 1, 0, 0},
            {0, 1, 0, 0, 1, 0, 0},
            {0, 1, 1, 1, 1, 0, 0}};
        Assertions.assertEquals(18, largestIsland(grid));

        grid = new int[][]{{0, 0}, {0, 0}};
        Assertions.assertEquals(1, largestIsland(grid));
    }

    private final int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int largestIsland(int[][] grid) {
        int size = grid.length;
        int reservedColor = 1;
        int[] colorCnt = new int[size*size+3];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (grid[r][c]==0 || grid[r][c] > 2) continue;
                reservedColor++;
                int cnt = colorize(grid, r, c, reservedColor);
                colorCnt[reservedColor] = cnt;
            }
        }
        int finalRes = 0;

        // no cell has been colorized ~ no cell is island
        if (reservedColor == 1) return 1;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (grid[r][c]!=0) continue;
                boolean[] visit = new boolean[reservedColor+1];
                int res = 1;
                for (int[] d : directions) {
                    // neighbor
                    int neiRow = r + d[0];
                    int neiCol = c + d[1];

                    // check outbound valid
                    if (isInvalid(neiRow, neiCol, size)) continue;

                    // check neighbor color has been visited yet
                    int neiColor = grid[neiRow][neiCol];
                    if (visit[neiColor]) continue;
                    visit[neiColor] = true;
                    res += colorCnt[neiColor];
                }

                finalRes = Math.max(finalRes, res);
            }
        }
        if (finalRes==0) return size * size;
        return finalRes;
    }
    int colorize(int[][] grid, int row, int col, int color) {
        if (isInvalid(row, col, grid.length)) {
            return 0;
        }
        // not water
        if (grid[row][col]==0) return 0;

        // colorized already
        if (grid[row][col] > 1) return 0;

        int res = 1;
        grid[row][col] = color;
        for (int[] d : directions) {
            res += colorize(grid, row + d[0], col + d[1], color);
        }

        return res;
    }
    boolean isInvalid(int row, int col, int size) {
        return row < 0 || row >= size || col < 0 || col >= size;
    }
}
