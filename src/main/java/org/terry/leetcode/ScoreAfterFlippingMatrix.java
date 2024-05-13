package org.terry.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 861. Score After Flipping Matrix
 Principal:
 0) with row = i, check matrix at row ith and col 0, aka the 1st col in the row. If it is '0', let's flip the row because
 the largest number will have the left-most bit is '1'.
 1) After the step 0, we check all the col in matrix and make sure number of bit '1' in matrix[i][col], i in [0, rowSize),
 is always greater than or equal to the number of bit '0', if not, we flip matrix[i][col].
 */
public class ScoreAfterFlippingMatrix {
	public static void main(String[] args) {
		new ScoreAfterFlippingMatrix().test();
	}
	public void test() {
		int[][] grid;
		int outptut;
		int actual;

		grid = new int[][]{{0, 0, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}};
		outptut = 39;
		actual = matrixScore(grid);
		assertEquals(outptut, actual);

		grid = new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 1, 0}};
		outptut = 20;
		actual = matrixScore(grid);
		assertEquals(outptut, actual);

		grid = new int[][]{{0}};
		outptut = 1;
		actual = matrixScore(grid);
		assertEquals(outptut, actual);

	}

	public int matrixScore(int[][] grid) {
		int rowSize = grid.length;
		int colSize = grid[0].length;

		for (int i = 0; i < rowSize; i++) {
			if (grid[i][0] == 0) {
				// flip cols
				for (int j = 0; j < colSize; j++) {
					grid[i][j] = Math.abs(grid[i][j] - 1);
				}
			}
		}

		for (int i = 0; i < colSize; i++) {
			int totalOneBit = 0;
			for (int j = 0; j < rowSize; j++) {
				totalOneBit += grid[j][i];
			}
			int totalZeroBit = rowSize - totalOneBit;
			if (totalOneBit > totalZeroBit) continue;

			for (int j = 0; j < rowSize; j++) {
				grid[j][i] = Math.abs(grid[j][i] - 1);
			}
		}

		int res = 0;
		for (int[] rows : grid) {
			int pow = colSize-1;
			for (int col : rows) {
				if (col == 1) res += Math.pow(2, pow);
				pow --;
			}
		}
		return res;
	}

	// used for testing matrix after flipping row, col values.
	private void print(int[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				System.out.printf("%3d", grid[i][j]);
			}
			System.out.println();
		}

		System.out.println();
	}

}
