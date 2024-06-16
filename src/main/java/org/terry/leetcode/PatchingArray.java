package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

/**
 * Leetcode 330: Patching array
 * Use 2 pointers: miss and nums[i], miss is the missing values, nums[i] is the current value in nums.
 *
 * if nums[i] <= miss + 1 (next miss) => increment miss with nums[i] and increment i.
 * else increment miss with next miss (miss + 1) and increment result.
 *
 * Complexity: O(n)
 * space constant.
 */
public class PatchingArray {
    public static void main(String[] args) {
        new PatchingArray().test();
    }

    void test() {
        int[] nums;
        int n;
        int output, actual;

        nums = new int[]{1, 3};
        n = 6;
        output = 1;
        actual = minPatches(nums, n);
        Assertions.assertEquals(output, actual);

        nums = new int[]{1, 5, 10};
        n = 20;
        output = 2;
        actual = minPatches(nums, n);
        Assertions.assertEquals(output, actual);

    }

    public int minPatches(int[] nums, int n) {
        int res = 0;
        long miss = 0L;
        int i = 0;
        while (miss < n) {
            if (i < nums.length && nums[i] <= miss + 1) {
                miss += nums[i ++];
            } else {
                miss += (miss+1);
                res ++;
            }
        }
        return res;
    }
}