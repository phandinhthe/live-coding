package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

public class RegionBySlashes {
    /**
     * Leetcode 959: Regions by slashes
     * https://leetcode.com/problems/regions-cut-by-slashes
     * <p>
     * Tuition:
     * - Convert from matrix [NxN] to `new matrix` [3Nx3N].
     * Then we could have:
     * [' ', ' /', '\']
     * <p>
     * [' ', ' ', ' '][,' ', ' ', '/',][ '\', ' ', ' ']
     * [' ', ' ', ' '][,' ', '/', ' ',][ ' ', '\', ' ']
     * [' ', ' ', ' '][,'/', ' ', ' ',][ ' ', ' ', '\']
     * <p>
     * apply DFS on the new matrix:
     * - if (row,col) has been visited: continue
     * - with every `row, col` in {[0, n], [0, n]} : dfs (row, col)
     * - - dfs(row, col) : make (row,col) `visited`, then visit the neighbor cells
     * - increase `res`
     * Finally, return `res`
     * <p>
     * Complexity: O(3*3*n*n)
     * Space:       O(3*3*n*n)
     */
    public static void main(String[] args) {
        new RegionBySlashes().test();
    }

    public void test() {
        String[] input;
        int expected, output;

        input = new String[]{" /", "/ "};
        output = regionsBySlashes(input);
        expected = 2;
        Assertions.assertEquals(expected, output);

        input = new String[]{" /", "  "};
        output = regionsBySlashes(input);
        expected = 1;
        Assertions.assertEquals(expected, output);

        input = new String[]{"/\\", "\\/"};
        output = regionsBySlashes(input);
        expected = 5;
        Assertions.assertEquals(expected, output);
    }

    private static final int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int regionsBySlashes(String[] grid) {
        char[][] newGrid = convertGrid(grid);
        int res = 0;
        for (int r = 0; r < newGrid.length; r++) {
            for (int c = 0; c < newGrid.length; c++) {
                if (newGrid[r][c] == '1') continue;
                dfs(newGrid, r, c, newGrid.length);
                res++;
            }
        }
        return res;
    }

    char[][] convertGrid(String[] grid) {
        char[][] newGrid = new char[grid.length * 3][grid.length * 3];
        for (int i = 0; i < grid.length; i++) {
            char[] arr = grid[i].toCharArray();
            int nRow = i * 3;
            for (int j = 0; j < arr.length; j++) {
                char c = arr[j];
                int nCol = j * 3;
                if (c == '/') {
                    newGrid[nRow][nCol + 2] = '1';
                    newGrid[nRow + 1][nCol + 1] = '1';
                    newGrid[nRow + 2][nCol] = '1';
                } else if (c == '\\') {
                    newGrid[nRow][nCol] = '1';
                    newGrid[nRow + 1][nCol + 1] = '1';
                    newGrid[nRow + 2][nCol + 2] = '1';
                }
            }
        }
        return newGrid;
    }

    public void dfs(char[][] grid, int row, int col, int length) {
        if (row < 0 || row == length) return;
        if (col < 0 || col == length) return;
        int val = grid[row][col];
        if (val == '1') return;
        grid[row][col] = '1';
        for (int i = 0; i < 4; i++) {
            dfs(grid, row + directions[i][0], col + directions[i][1], length);
        }
    }
}
