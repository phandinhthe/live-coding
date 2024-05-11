package org.terry.leetcode;

import java.util.ArrayList;
import java.util.List;

public class FindAllGroupsOfFarmLand {
	/*
		You are given a 0-indexed m x n binary matrix land where a 0 represents a hectare of forested land and a 1 represents a hectare of farmland.

		To keep the land organized, there are designated rectangular areas of hectares that consist entirely of farmland.
		These rectangular areas are called groups. No two groups are adjacent, meaning farmland in one group is not four-directionally adjacent to another farmland in a different group.
		land can be represented by a coordinate system where the top left corner of land is (0, 0) and the bottom right corner of land is (m-1, n-1).
		Find the coordinates of the top left and bottom right corner of each group of farmland.
		A group of farmland with a top left corner at (r1, c1) and a bottom right corner at (r2, c2) is represented by the 4-length array [r1, c1, r2, c2].
		Return a 2D array containing the 4-length arrays described above for each group of farmland in land.

		If there are no groups of farmland, return an empty array. You may return the answer in any order.

		** constraint **
		m == land.length
		n == land[i].length
		1 <= m, n <= 300
		land consists of only 0's and 1's.
		Groups of farmland are rectangular in shape.
	*/

	/*
		 1992. Find all groups of Farmland.
		 - Find the top left cell
		 - From the top left cell -> find the botRight
		 O(n*n*m): n is the row size and m is the column size
	*/
	public static void main(String[] args) {
		new Solution().test();
	}


	static class Solution {
		public void test() {

			int[][] land;
			int[][] output;
			int[][] actual;

			land = new int[][]{{1, 0, 0}, {0, 1, 1}, {0, 1, 1}};
			actual = findFarmland(land);
			output = new int[][]{{0, 0, 0, 0}, {1, 1, 2, 2}};
			assert comapare(output, actual);

			land = new int[][]{{1, 1}, {1, 1}};
			actual = findFarmland(land);
			output = new int[][]{{0, 0, 1, 1}};
			assert comapare(output, actual);

			land = new int[][]{{0}};
			actual = findFarmland(land);
			output = new int[][]{};
			assert comapare(output, actual);


			land = new int[][]{
					{1, 1, 0, 0, 0, 1},
					{1, 1, 0, 0, 0, 0}
			};
			actual = findFarmland(land);
			output = new int[][]{{0, 0, 1, 1}, {0, 5, 0, 5}};
			assert comapare(output, actual);

			land = new int[][]{{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
			actual = findFarmland(land);
			output = new int[][]{{0,0,0,11}};
			assert comapare(output, actual);
		}

		public int[][] findFarmland(int[][] land) {
			int rowSize = land.length;
			int colSize = land[0].length;
			FunctionFindBotRight functionFindBotRight = (row, col) -> {
				int iRow = row, iCol = col;
				while (iRow + 1 < rowSize && land[iRow + 1][col] == 1) iRow++;
				while (iCol + 1 < colSize && land[row][iCol + 1] == 1) iCol++;
				return new int[]{iRow, iCol};
			};

			FunctionFarmLandCheck functionFarmLandCheck = (row, col) -> {
				if (row < 0 || col < 0 || row == land.length || col == land[0].length) return true;
				return land[row][col] == 0;
			};

			List<int[]> ans = new ArrayList<>();
			for (int row = 0; row < rowSize; row++) {
				for (int col = 0; col < colSize; col++) {
					if (land[row][col] == 0)
						continue;
					if (functionFarmLandCheck.isNotLand(row, col - 1) && functionFarmLandCheck.isNotLand(row - 1, col)) {
						int[] botRight = functionFindBotRight.findBotRight(row, col);
						ans.add(new int[]{row, col, botRight[0], botRight[1]});
					}
				}
			}
			return ans.toArray(size -> new int[size][4]);
		}

		interface FunctionFindBotRight {
			int[] findBotRight(int row, int col);

			default int[] doFindBotRight(int row, int col) {
				return findBotRight(row, col);
			}


		}

		interface FunctionFarmLandCheck {
			boolean isNotLand(int row, int col);
		}

		boolean comapare(int[][] matrix1, int[][] matrix2) {
			if (matrix2 == matrix1) return true;
			if (matrix1.length != matrix2.length) return false;
			for (int row = 0; row < matrix1.length; row++) {
				if (matrix1[row] == matrix2[row]) continue;
				for (int col = 0; col < matrix1[0].length; col++) {
					if (matrix1[row][col] != matrix2[row][col]) return false;
				}
			}

			return true;
		}

	}
}
