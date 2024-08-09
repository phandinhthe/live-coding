package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.Set;

/**
 * Leetcode 840: Magic Squares in Grid.
 * https://leetcode.com/problems/magic-squares-in-grid/description/
 */
public class MagicSquaresInGrid {
    public static void main(String[] args) {
        new MagicSquaresInGrid().test();
    }

    public void test() {
        int[][] grid;
        int expected, output;

        grid = new int[][] {{4,3,8,4},{9,5,1,9},{2,7,6,2}};
        expected = 1;
        output = numMagicSquaresInside(grid);
        Assertions.assertEquals(expected, output);

        grid = new int[][] {{3,2,9,2,7},{6,1,8,4,2},{7,5,3,2,7},{2,9,4,9,6},{4,3,8,2,5}};
        expected = 1;
        output = numMagicSquaresInside(grid);
        Assertions.assertEquals(expected, output);

    }

    public int numMagicSquaresInside(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int res = 0;
        for (int r = 0; r < rows - 2; r++) {
            for (int c = 0; c < cols - 2; c++) {
                if (isMagic(grid, r, c))
                    res++;
            }
        }
        return res;
    }

    boolean isMagic(int[][] grid, int row, int col) {
        int[] sumR = new int[3];
        int[] sumC = new int[3];
        int[] sumD = new int[3];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int r = row + i;
                int c = col + j;
                int val = grid[r][c];
                if (val > 9 || set.contains(val)) return false;
                set.add(val);
                sumR[i] += val;
                sumC[j] += val;
                if (i + j == 2)
                    sumD[2] += val;
                if (i - j == 0)
                    sumD[0] += val;
                if (sumR[i] > 15 || sumC[j] > 15 || sumD[0] > 15 || sumD[2] > 15)
                    return false;

            }
        }
        if (sumR[0] != 15 || sumR[1] != 15 || sumR[2] != 15) return false;
        if (sumC[0] != 15 || sumC[1] != 15 || sumC[2] != 15) return false;
        return sumD[0] == 15 && sumD[2] == 15;
    }


}
