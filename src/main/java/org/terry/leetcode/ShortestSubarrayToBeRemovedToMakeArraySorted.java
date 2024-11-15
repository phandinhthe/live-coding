package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

/**
 * <a href="https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/description/?envType=daily-question&envId=2024-11-15">Shortes Subarray to be removed to make array sorted</a>
 *
 * Tuition:
 * - 2 pointers
 * - find left is the last index of non-decreasing left side, right is the first index of the non-decreasing right side array
 * - from right most, iterate i until a[i] < a[i-1]. The indicator of start point for non-decreasing of right pointer.
 * - set result = r // the max result could be found for removal
 * - with left = 0:
 * - - move right pointer backward if a[l] > a[r], until we found a[r] >= a[l]
 * - - move left pointer forward if a[l] <= a[r]
 * - - result = (r-l-1)
 * - - and stop if left == right or a[l] < arr[l-1]
 *
 */


public class ShortestSubarrayToBeRemovedToMakeArraySorted {
    public static void main(String[] args) {
        new ShortestSubarrayToBeRemovedToMakeArraySorted().run();
    }

    public void run() {
        // input
        int[] arr;
        int expected;

        arr = new int[]{1, 2, 3, 10, 4, 2, 3, 5};
        expected = 3;
        Assertions.assertEquals(expected, findLengthOfShortestSubarray(arr));

        arr = new int[]{2, 2, 2, 1, 1, 1};
        expected = 3;
        Assertions.assertEquals(expected, findLengthOfShortestSubarray(arr));

        arr = new int[]{5, 4, 3, 2, 1};
        expected = 4;
        Assertions.assertEquals(expected, findLengthOfShortestSubarray(arr));

        arr = new int[]{1, 2, 3};
        expected = 0;
        Assertions.assertEquals(expected, findLengthOfShortestSubarray(arr));

        arr = new int[]{1, 3, 2, 4};
        expected = 1;
        Assertions.assertEquals(expected, findLengthOfShortestSubarray(arr));

        arr = new int[]{13,0,14,7,18,18,18,16,8,15,20};
        expected = 8;
        Assertions.assertEquals(expected, findLengthOfShortestSubarray(arr));

    }

    public int findLengthOfShortestSubarray(int[] arr) {
        int l = 0;
        int r = arr.length - 1;

        while (r > 0 && arr[r] >= arr[r-1]) r--;
        int res = r;

        while (l < r) {
            if (l > 0 && arr[l] < arr[l-1]) break;
            while(r < arr.length && arr[l] > arr[r]) {
                r ++;
            }
            res = Math.min(res, r - l - 1);
            l ++;
        }

        return res;
    }
}
