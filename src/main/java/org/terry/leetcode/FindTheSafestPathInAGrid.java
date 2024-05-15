package org.terry.leetcode;


import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 2812. Find the Safest Path in a Grid
 * https://leetcode.com/problems/find-the-safest-path-in-a-grid
 * <p>
 * Principal:
 * - find all the thief (Complexity: n^2)
 * - find the minimum safety in every not-thief cell (Complexity: n^2)
 * - find the safest path: using PriorityQueue, check all visited cell until reaching the goal cell(rowSize-1, colSize-1)
 * (Complexity: n^2*log(n))
 */
public class FindTheSafestPathInAGrid {

	public static void main(String[] args) {
		new FindTheSafestPathInAGrid().test();
	}

	public void test() {
		int[][] grid;
		int output;
		int actual;

		grid = new int[][]{{0, 6, 0}, {5, 8, 7}, {0, 9, 0}};
		actual = getMaximumGold(grid);
		output = 24;
		assertEquals(output, actual);

		grid = new int[][]{{1, 0, 7}, {2, 0, 6}, {3, 4, 5}, {0, 3, 0}, {9, 0, 20}};
		actual = getMaximumGold(grid);
		output = 28;
		assertEquals(output, actual);

		grid = new int[][]{{1, 1, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 1, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 1, 1, 1, 1}};
		actual = getMaximumGold(grid);
		output = 19;
		assertEquals(output, actual);
	}

	private int[][] grid;
	private final int[][] moves = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	public int getMaximumGold(int[][] grid) {
		int rowSize = grid.length;
		int colSize = grid[0].length;
		this.grid = grid;
		int res = 0;
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (grid[row][col] != 0) {
					res = Math.max(res, dfs(rowSize, colSize, row, col));
				}
			}
		}
		return res;
	}


	public int dfs(int rowSize, int colSize, int currentRow, int currentCol) {
		if (!check(rowSize, colSize, currentRow, currentCol)) return 0;

		int result = 0;
		int backup = grid[currentRow][currentCol];
		grid[currentRow][currentCol] = 0;
		for (int[] move : moves) {
			int newRow = currentRow + move[0];
			int newCol = currentCol + move[1];
			result = Math.max(result, dfs(rowSize, colSize, newRow, newCol));
		}
		grid[currentRow][currentCol] = backup;
		return result + grid[currentRow][currentCol];
	}

	public boolean check(int rowSize, int colSize, int row, int col) {
		if (row < 0 || col < 0) return false;
		if (row >= rowSize || col >= colSize) return false;
		return grid[row][col] != 0;
	}
}