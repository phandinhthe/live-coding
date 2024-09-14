package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.stream.IntStream;

/**
 * <a href="">Leetcode 2419. Longest Subarray with maximum bietwise AND</a>
 * https://leetcode.com/problems/longest-subarray-with-maximum-bitwise-and
 * <p/>
 * Tuition:
 * - Apply `Kadane`
 * - find the max.
 * - iterate nums
 * - - if : nums[i] < max -> update `size` = 0
 * - - else if : nums[i] == max -> `size` ++
 * - return max of size
 * <p/>
 * Complexity: O(n)
 * Space: O(1)
 */
public class LongestSubArrayWIthMaximumBitwiseAnd {
    public static void main(String[] args) {
        new LongestSubArrayWIthMaximumBitwiseAnd().run();
    }


    public void run() {
        int[] nums;
        int expected;

        nums = new int[]{1, 2, 3, 3, 2, 2};
        Assertions.assertEquals(2, longestSubarray(nums));
        nums = new int[]{1, 2, 3, 4};
        Assertions.assertEquals(1, longestSubarray(nums));
        nums = new int[]{96317, 96317, 96317, 96317, 96317, 96317, 96317, 96317, 96317, 279979};
        Assertions.assertEquals(1, longestSubarray(nums));
    }

    public int longestSubarray(int[] nums) {
        int size = 0, res = 0;
        int max = IntStream.of(nums).max().getAsInt();
        for (int n : nums) {
            if (n < max) size = 0;
            else size++;

            res = Math.max(res, size);
        }
        return res;
    }

}
