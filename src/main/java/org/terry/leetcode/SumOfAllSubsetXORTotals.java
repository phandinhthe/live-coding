package org.terry.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumOfAllSubsetXORTotals {
	public static void main(String[] args) {
		new	SumOfAllSubsetXORTotals().test();
	}

	public void test() {
		int nums[];
		int output;
		int actual;

		nums = new int[]{1, 3};
		actual = subsetXORSum(nums);
		output = 6;
		assertEquals(output, actual);

		nums = new int[]{5, 1, 6};
		actual = subsetXORSum(nums);
		output = 28;
		assertEquals(output, actual);

		nums = new int[]{3, 4, 5, 6, 7, 8};
		actual = subsetXORSum(nums);
		output = 480;
		assertEquals(output, actual);
	}

	public int subsetXORSum(int[] nums) {
		return bfs(-1, 0, nums.length, nums);
	}

	int bfs(int index, int xor, int size, int[] nums) {
		int res = 0;
		for (int next = index + 1; next < size; next++) {
			res += (xor ^ nums[next]);
			res += bfs(next, xor ^ nums[next], size, nums);
		}
		return res;
	}


}
