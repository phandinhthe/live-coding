package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

/**
 * 1442. Count Triplets That Can Form Two Arrays of Equal XOR
 * https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor
 * <p>
 * - Find sub-array in `arr`
 * - total xor sub-array a[i ...j] == 0 => we have (i-j) results.
 * - loop over [i...arr.length-2] and nested [j=i+1, arr.length-1], check arr[i] == xor of arr[i+1,j]
 * Complexity: O(n^2)
 * Space:		O(1)
 */
public class CountTripletsThatCanFormTwoArraysOfEqualXor {
	public static void main(String[] args) {
		new CountTripletsThatCanFormTwoArraysOfEqualXor().test();
	}

	public void test() {
		int[] arr;
		int output;
		int actual;

		arr = new int[]{2, 3, 1, 6, 7};
		actual = countTriplets(arr);
		output = 4;
		Assertions.assertEquals(output, actual);

		arr = new int[]{1, 1, 1, 1, 1};
		actual = countTriplets(arr);
		output = 10;
		Assertions.assertEquals(output, actual);

		arr = new int[]{1};
		actual = countTriplets(arr);
		output = 0;
		Assertions.assertEquals(output, actual);
	}

	public int countTriplets(int[] arr) {
		int res = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			int xor = 0;
			for (int j = i + 1; j < arr.length; j++) {
				xor ^= arr[j];
				if (arr[i] == xor) res += (j - i);
			}
		}
		return res;
	}

}
