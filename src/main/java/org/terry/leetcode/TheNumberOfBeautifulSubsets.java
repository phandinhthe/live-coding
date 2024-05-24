package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class TheNumberOfBeautifulSubsets {
	public static void main(String[] args) {
		new TheNumberOfBeautifulSubsets().test();
	}

	public void test() {
		int[] nums;
		int k;
		int output;
		int actual;

		nums = new int[]{2, 4, 6};
		k = 2;
		output = 4;
		actual = new BruteForceUsingInvalidCountSet().beautifulSubsets(nums, k);
		Assertions.assertEquals(output, actual);

		nums = new int[]{1};
		k = 1;
		output = 1;
		actual = new BruteForceUsingInvalidCountSet().beautifulSubsets(nums, k);
		Assertions.assertEquals(output, actual);

		nums = new int[]{4, 2, 5, 9, 10, 3};
		k = 1;
		output = 23;
		actual = new BruteForceUsingInvalidCountSet().beautifulSubsets(nums, k);
		Assertions.assertEquals(output, actual);
	}

	/**
	 * Brute force:
	 * - Use a Map or an Arrray to count the appearance of nums[i] + 7 and nums[i] - 7(i: [0,n).
	 * When cur index is at 'i', check the invalid number of nums[i] and skip if the invalid is already in the invalid count array.
	 * <p>
	 * Complexity: O(2^n)
	 * Space: O(Max(nums[i] + k)
	 */
	public class BruteForceUsingInvalidCountSet {
		public int beautifulSubsets(int[] nums, int k) {
			final int MAX = 1001 + k;
			res = 0;
			this.k = k;
			invalid = new int[MAX + 7];
			dfs(0, nums);
			return res - 1;
		}

		int res = 0;
		int k;
		int[] invalid;

		void dfs(int cur, int[] nums) {
			if (cur == nums.length) {
				res++;
				return;
			}
			int n = nums[cur];
			boolean valid = invalid[n + k] == 0;
			if (n - k >= 0)
				valid = valid && invalid[n - k] == 0;
			if (valid) {
				invalid[n]++;
				dfs(cur + 1, nums);
				invalid[n]--;
			}
			dfs(cur + 1, nums);
		}
	}

	/**
	 * Brute force.
	 * <p>
	 * List out all the subset and check every number valid or not.
	 * Complexity: O(n * 2^n)
	 */
	public class BruteForce {
		public int beautifulSubsets(int[] nums, int k) {
			this.k = k;
			res = 0;
			dfs(new ArrayList<>(), 0, nums);
			return res;
		}

		int res = 0;
		int k;

		void dfs(List<Integer> subset, int idx, int[] nums) {
			if (idx == nums.length) {
				res = (subset.isEmpty()) ? res : res + 1;
				System.out.println(subset);
				return;
			}

			boolean valid = true;
			for (int n : subset) {
				if (Math.abs(nums[idx] - n) == k) {
					valid = false;
					break;
				}
			}

			if (valid) {
				subset.add(nums[idx]);
				dfs(subset, idx + 1, nums);
				subset.removeLast();
			}
			dfs(subset, idx + 1, nums);
		}

	}
}
