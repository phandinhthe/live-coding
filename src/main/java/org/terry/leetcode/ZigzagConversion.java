package org.terry.leetcode;
/*
	6. Zigzag Conversion
	zig zag converting
	- use 2 indexes: index1 and index2
	- increment continually by 'distance' until the end of STring. => we have result for one row.
	- calculate distance = (numRows - 1) * 2;
	- Be careful:
		+ Select the initial 'index1' vs 'index2' per row -> formula: i, distance - i
		+ at the 'start' and the 'end' row, we could have overlapping of 2 indexes (the edge case)
			* index1 equals index2 => overlap at the start of dfs processing
			* index1 + distance = index2 => overlap when processing dfs
		+ with intput 's' have length == 1 and distance == 1, return s immediately
 */
public class ZigzagConversion {
	public static void main(String[] args) {
		new Solution().test();
	}

	public static class Solution {
		public void test() {
			String s;
			int rows;
			String output;
			String actual;

			s = "PAYPALISHIRING";
			rows = 3;
			output = "PAHNAPLSIIGYIR";
			actual = convert(s, rows);
			assert actual.equals(output);

			s = "PAYPALISHIRING";
			rows = 4;
			output = "PINALSIGYAHRPI";
			actual = convert(s, rows);
			assert actual.equals(output);

			s = "A";
			rows = 1;
			output = "A";
			actual = convert(s, rows);
			assert actual.equals(output);

		}

		/*
			6. Zigzag Conversion
			zig zag converting
			- use 2 indexes: index1 and index2
			- increment continually by 'distance' until the end of STring. => we have result for one row.
			- calculate distance = (numRows - 1) * 2;
			- Be careful:
				+ Select the initial 'index1' vs 'index2' per row -> formula: i, distance - i
				+ at the 'start' and the 'end' row, we could have overlapping of 2 indexes (the edge case)
					* index1 equals index2 => overlap at the start of dfs processing
					* index1 + distance = index2 => overlap when processing dfs
				+ with intput 's' have length == 1 and distance == 1, return s immediately
		 */
		public String convert(String s, int numRows) {
			int distance = (numRows - 1) * 2;
			if (distance == 0) return s;
			StringBuilder ansBuilder = new StringBuilder();
			for (int i = 0; i < numRows; i++) {
				ansBuilder = dfs(s, ansBuilder, i, distance - i, distance);
			}

			return ansBuilder.toString();
		}

		public StringBuilder dfs(String s, StringBuilder rs, int index1, int index2, int distance) {
			if (index1 >= s.length() && index2 >= s.length()) return rs;

			if (index1 < s.length()) rs.append(s.charAt(index1));
			if (index2 < s.length() && index1 != index2 && index1 + distance != index2) rs.append(s.charAt(index2));
			return dfs(s, rs, index1 + distance, index2 + distance, distance);
		}
	}
}
