package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * 945. Minimum Increment to Make Array Unique
 * https://leetcode.com/problems/minimum-increment-to-make-array-unique
 *
 * - Sorting in ascending order.
 * - 2 Pointers: prev, cur.
 *      + If cur > prev: (valid) prev = cur; continue;
 *      + Else (invalid): prev ++; result = result + (prev - cur);
 **/
public class MinimumIncrementToMakeArrayUnique {
    public static void main(String[] args) {
        new MinimumIncrementToMakeArrayUnique().test();
    }

    public void test() {
        int[] nums;
        int expected;

        nums = new int[]{1, 2, 2};
        expected = 1;
        assertTest(nums, expected);

        nums = new int[]{3, 2, 1, 2, 1, 7};
        expected = 6;
        assertTest(nums, expected);

    }

    private void assertTest(int[] nums, int expected) {
        int actual = minIncrementForUnique(nums);
        Assertions.assertEquals(expected, actual);
    }

    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int prev = nums[0] - 1;
        int res = 0;
        for (int n : nums) {
            if (n > prev) prev = n;
            else {
                prev ++;
                res += (prev - n);
            }
        }
        return res;
    }
}
