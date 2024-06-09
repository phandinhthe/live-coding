package org.terry.leetcode.hard;

import org.junit.jupiter.api.Assertions;

/**
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/
 * Leetcode 974: SubArray sums divisible by K.
 */
public class SubarraysSumsDivideByK {
	public static void main(String[] args) {
		new SubarraysSumsDivideByK().test();
	}

	public void test() {
		int[] nums;
		int k;
		int output;

		nums = new int[]{4, 5, 0, -2, -3, 1};
		k = 5;
		output = 7;
		assertEquals(nums, k, output);

		nums = new int[]{5};
		k = 9;
		output = 0;
		assertEquals(nums, k, output);

		nums = new int[]{-1, 2, 9};
		k = 2;
		output = 2;
		assertEquals(nums, k, output);

		nums = new int[]{7, -5, 5, -8, -6, 6, -4, 7, -8, -7};
		k = 7;
		output = 11;
		assertEquals(nums, k, output);

	}

	public void assertEquals(int[] nums, int k, int output) {
		Assertions.assertEquals(output, subarraysDivByK(nums, k));
	}

	/**
	 * Apply `prefix sum` method.
	 * Find `diff` = sum % number[i].
	 * If `prefix` of number[i] has happent before, res += `prefix`[number ith]
	 * Else put `prefix` [number ith] to dictionary with increment value.
	 *
	 * Edge case: if sum = 0, increment res;
	 * if diff < 0, convert diff = k + diff.
	 * Beacause with diff = -1, k = 5, we could calculate diff = k + diff to have final diff = 4.
	 */
	public int subarraysDivByK(int[] nums, int k) {
		// Dictionary to record the prefix sum
		// We could use a Map, but array is better peformance.
		int[] prefixes = new int[k];

		int res = 0;
		int diff; // diff
		int sum = 0; // sum
		for (int n : nums) {
			sum += n;
			diff = sum % k;
			if (diff < 0) diff = diff + k; // diff < 0 => convert to k+diff.Exam: diff = -2, k = 5 => diff = 3.
			else if (diff == 0) res++; // edge case when sum could mod k without remaining.
			res += prefixes[diff];
			prefixes[diff] ++;
		}
		return res;
	}
}
