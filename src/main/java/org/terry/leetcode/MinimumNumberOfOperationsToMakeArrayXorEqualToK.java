package org.terry.leetcode;

/**
 * Leetcode 2997: Minimum Number of Operations to make  Array XOR equal to k.
 * Principal:
 * - Calculate the final xor result of all numbers in 'nums'. Then convert the final xor result to 'binary'.
 * - Calculate the binary string of  'k'.
 * - The number of differences between bits in final xor result of 'nums' and 'k' are the min operations we expected.
 * <p>
 * Xor all numbers in the 'nums' : complexity O(n)
 * convert to binary String for xorAllNums and k: O(logn)
 * 2 pointers to compare 2 binary strings: O(n)
 * => Complexity O(n), space O(n)
 * <p>
 **/
public class MinimumNumberOfOperationsToMakeArrayXorEqualToK {
	public static void main(String[] args) {
		new MinimumNumberOfOperationsToMakeArrayXorEqualToK().test();
	}

	public void test() {

		int[] nums = new int[]{2, 1, 3, 4};
		int k = 1;
		int output = 2;
		int actual = optimizedMinOperations(nums, k);
		assert actual == output;

		nums = new int[]{2, 0, 2, 0};
		k = 0;
		output = 0;
		actual = optimizedMinOperations(nums, k);
		assert actual == output;

		nums = new int[]{1};
		k = 1;
		output = 0;
		actual = optimizedMinOperations(nums, k);
		assert actual == output;

		nums = new int[]{9, 7, 9, 14, 8, 6};
		k = 12;
		output = 3;
		actual = optimizedMinOperations(nums, k);
		assert actual == output;
	}

	/**
	 * @param nums - array of numbers
	 * @param k    - target number
	 * @return the minimum of Xor operations to make final xor result in 'nums' equal to 'k'.
	 */
	public int minOperations(int[] nums, int k) {
		int xorAll = 0;
		for (int number : nums) {
			xorAll ^= number;
		}

		String xorAllBinary = Integer.toBinaryString(xorAll);
		String xorK = Integer.toBinaryString(k);

		int i = xorAllBinary.length() - 1, j = xorK.length() - 1;
		int ans = 0;
		while (i >= 0 && j >= 0) {
			if (xorAllBinary.charAt(i) != xorK.charAt(j)) ans++;
			i--;
			j--;
		}

		while (i >= 0) {
			if (xorAllBinary.charAt(i) == '1') ans++;
			i--;
		}
		while (j >= 0) {
			if (xorK.charAt(j) == '1') ans++;
			j--;
		}

		return ans;
	}

	/*
		Optimal solution:
		- we divide and compare the modulo '2' of final xor 'nums' and k continually
		- every difference when comapring the modulo results, the result will be incremented
		complexity O(logn)
	 */
	public int optimizedMinOperations(int[] nums, int k) {
		int xorNums = 0;
		for (int num : nums) {
			xorNums ^= num;
		}

		int ans = 0;
		while (xorNums != 0 || k != 0) {

			if (xorNums % 2 != k % 2) {
				ans++;
			}
			xorNums /= 2;
			k /= 2;
		}
		return ans;
	}
}
