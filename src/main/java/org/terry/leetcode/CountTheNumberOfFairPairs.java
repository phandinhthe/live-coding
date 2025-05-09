package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/count-the-number-of-fair-pairs/description/?envType=daily-question&envId=2024-11-13">Count the number of fair pairs</a>
 */
public class CountTheNumberOfFairPairs {
    public static void main(String[] args) {
        new CountTheNumberOfFairPairs().run();
    }

    public void run() {
        // input
        int[] nums;
        int lower, upper;
        long expected;

        nums = new int[]{0, 1, 7, 4, 4, 5};
        lower = 3;
        upper = 6;
        expected = 6L;
        Assertions.assertEquals(expected, countFairPairs(nums, lower, upper));

        nums = new int[]{1, 7, 9, 2, 5};
        lower = 11;
        upper = 11;
        expected = 1L;
        Assertions.assertEquals(expected, countFairPairs(nums, lower, upper));

    }

    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        return count(nums, upper) - count(nums, lower - 1);
    }

    public long count(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        long res = 0;
        while (left < right) {
            long total = nums[left] + nums[right];
            if (total > target) {
                right --;
            } else {
                res += (right - left);
                left ++;
            }
        }

        return res;

    }
    /* Test GH CLI
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int i = 0;
        int res = 0;
        while (i < nums.length) {
            int r = binarySearch(nums, i + 1, nums.length - 1, upper - nums[i] + 1);
            int l = binarySearch(nums, i + 1, nums.length - 1, lower - nums[i]);
            res += (r - l);
            i++;
        }
        return res;
    }

    public int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return right;
    }
*/
}
