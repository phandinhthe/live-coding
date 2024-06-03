package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/single-number-iii
 * Single number III
 * <p>
 * step 1: find xor of all unique numbers
 * step 2: find the right most bit that represent the difference between 2 unique numbers
 * step 3: group all numbers into 2 groups: 1 group has diffBit & number != 0, 1 group has diffBit & number == 0
 * step 4: xor all values in group 1 => first unique number, the same for all values in group 2 to have second unique number
 */
public class SingleNumberIII {
	public static void main(String[] args) {
		new SingleNumberIII().test();
	}

	public void test() {
		int[] nums;
		int[] output;
		int[] actual;

		nums = new int[]{1, 2, 1, 3, 2, 5};
		output = new int[]{3, 5};
		actual = singleNumber(nums);
		Assertions.assertEquals(IntStream.of(output).boxed().collect(Collectors.toSet()), IntStream.of(actual).boxed().collect(Collectors.toSet()));

		nums = new int[]{-1, 0};
		output = new int[]{-1, 0};
		actual = singleNumber(nums);
		Assertions.assertEquals(IntStream.of(output).boxed().collect(Collectors.toSet()), IntStream.of(actual).boxed().collect(Collectors.toSet()));

		nums = new int[]{0, 1};
		output = new int[]{0, 1};
		actual = singleNumber(nums);
		Assertions.assertEquals(IntStream.of(output).boxed().collect(Collectors.toSet()), IntStream.of(actual).boxed().collect(Collectors.toSet()));
	}

	public int[] singleNumber(int[] nums) {
		// xor all values to find the xor of 2 unique numbers.
		int xor = 0;
		for (int n : nums) xor ^= n;

		// find the right most bit that respresent the difference.
		int diff = 1;
		while ((diff & xor) == 0) diff <<= 1;

		// group in to 2 groups and xor all values in every group to find result.
		int a = 0, b = 0;
		for (int n : nums) {
			if ((diff & n) == 0) a ^= n;
			else b ^= n;
		}
		return new int[]{b, a};
	}
}
