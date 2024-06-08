package org.terry.leetcode.hard;

/*
https://leetcode.com/problems/continuous-subarray-sum
523. Continuous Subarray Sum
Apply prefix-sum.
1. Check the edge case with 2 consecutive elements that mod k == 0.
2. Check the rest case - ignore the elements that its value mod k == 0.
Complexity: O(n)
 */

import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.Set;

public class ContinuousSubarraySums {
	public static void main(String[] args) {
		new ContinuousSubarraySums().test();
	}

	public void doTest(int[] nums, int k, boolean expected) {
		boolean actual = checkSubarraySum(nums, k);
		Assertions.assertEquals(expected, actual);
	}

	public void test() {
		int[] nums;
		nums = new int[]{23, 2, 4, 6, 7};
		doTest(nums, 6, true);

		nums = new int[]{23, 2, 6, 4, 7};
		doTest(nums, 6, true);

		nums = new int[]{23, 2, 6, 4, 7};
		doTest(nums, 13, false);

		nums = new int[]{0, 1, 0, 3, 0, 4, 0, 4, 0};
		doTest(nums, 5, false);

		nums = new int[]{1, 2, 12};
		doTest(nums, 6, false);

		nums = new int[]{5, 2, 4};
		doTest(nums, 5, false);

		nums = new int[]{0, 1, 0, 3, 0, 4, 0, 4, 0};
		doTest(nums, 5, false);

		nums = new int[]{0};
		doTest(nums, 1, false);

		nums = new int[]{1, 0};
		doTest(nums, 2, false);

		nums = new int[]{0, 0};
		doTest(nums, 1, true);

		nums = new int[]{5, 0, 0, 0};
		doTest(nums, 3, true);

		nums = new int[]{23, 2, 4, 6, 6};
		doTest(nums, 7, true);
	}

	public boolean checkSubarraySum(int[] nums, int k) {
		// Edge case: when length of element = 1.
		if (nums.length == 1) return false;

		// Edge case: when we have nums[i] % k == 0 and nums[i-1] % k == 0
		// Exam: [...0,0...], [..., 0, 5, ...] k = 5.
		int prev = -1;
		for (int num : nums) {
			if (num % k == 0 && prev % k == 0) return true;
			prev = num;
		}

		// for consecutive non-zero and  non-k value elements
		Set<Integer> prefixes = new HashSet<>();
		int sum = 0;
		for (int num : nums) {
			if (num % k == 0) continue;// ignore the nums[i] == 0 and k.
			sum += num;
			int diff = sum % k;

			// if the `diff` value is zero, return true because the sum % k == 0.
			// if the `diff` has existed in `prefixes`, return true.
			if (diff == 0 || prefixes.contains(diff))
				return true;
			prefixes.add(diff);// append the `diff` value.
		}
		return false;

	}

}
