package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

/*
https://leetcode.com/problems/relative-sort-array
Leetcode 1122: Relative Sort Array

Principal:
- loop over the first array `arr1` to count all the appearances of all the elements.
- loop over the second array `arr2` to append all the count of elements in order of `arr2`'s elements.
- loop over the range(0, 1001) to append the rest elements that not in `arr2`.

Complexity: O(n)
Space: O(n)
 */
public class RelativeSortArray {
	public static void main(String[] args) {
		new RelativeSortArray().test();
	}

	public void test() {
		int[] arr1, arr2;
		arr1 = new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
		arr2 = new int[]{2, 1, 4, 3, 9, 6};
		Assertions.assertArrayEquals(new int[]{2, 2, 2, 1, 4, 3, 3, 9, 6, 7, 19}, relativeSortArray(arr1, arr2));
		arr1 = new int[]{28,6,22,8,44,17};
		arr2 = new int[]{22,28,8,6};
		Assertions.assertArrayEquals(new int[]{22, 28, 8, 6, 17, 44}, relativeSortArray(arr1, arr2));
	}

	public int[] relativeSortArray(int[] arr1, int[] arr2) {
		final int MAX_SIZE = 1001;
		int[] count = new int[MAX_SIZE];
		for (int n : arr1) {
			count[n]++;
		}

		int[] res = new int[arr1.length];
		int i = 0;
		for (int n : arr2) {
			if (count[n] == 0) continue;

			while (count[n] > 0) {
				res[i++] = n;
				count[n]--;
			}
		}
		for (int n = 0; n < MAX_SIZE; n++) {
			if (count[n] == 0) continue;
			while (count[n] > 0) {
				res[i++] = n;
				count[n]--;
			}
		}
		return res;
	}
}
