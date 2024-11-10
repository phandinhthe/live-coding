package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

/**
 * <a href="https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii">
 * Shortest subarray iwth or at least k II</a>
 */
public class ShortestSubarrayWithORAtLeastKII {
    public static void main(String[] args) {
        new ShortestSubarrayWithORAtLeastKII().run();
    }

    public void run() {
        // Input
        int[] nums;
        int k;

        nums = new int[]{1, 2, 3};
        k = 2;
        Assertions.assertEquals(1, minimumSubarrayLength(nums, k));

        nums = new int[]{2, 1, 8};
        k = 10;
        Assertions.assertEquals(3, minimumSubarrayLength(nums, k));

        nums = new int[] {1, 2};
        k = 0;
        Assertions.assertEquals(1, minimumSubarrayLength(nums, k));

        nums = new int[] {1, 2, 32, 21};
        k = 55;
        Assertions.assertEquals(3, minimumSubarrayLength(nums, k));

        nums = new int[] {1,52,50,12,53,50,54,2};
        k = 55;
        Assertions.assertEquals(2, minimumSubarrayLength(nums, k));
    }

    public int minimumSubarrayLength(int[] nums, int k) {
        int[] bitCnt = new int[32];
        int r = 0;
        int l = 0;
        int size = 2*100_000;
        for (; r < nums.length; r ++) {
            or(bitCnt, nums[r]);
            while (l <= r && number(bitCnt) >= k) {
                size = Math.min(size, r - l + 1);
                remove(bitCnt, nums[l]);
                l ++;
            }
        }
        return size == 2*100_000 ? -1 : size;
    }

    public void or(int[] bitCnt, int n) {
        int i = 0;
        while (n > 0) {
            if (n % 2 == 1) bitCnt[i] ++;
            n /= 2;
            i ++;
        }
    }

    public void remove(int[] bitCnt, int n) {
       int i = 0;
       while (n > 0) {
           if (n %2 == 1 && bitCnt[i] > 0) bitCnt[i] --;
           n /= 2;
           i ++;
       }
    }

    public long number(int[] bitCnt) {
        long rs = 0;
        for (int i = 0; i < bitCnt.length; i++) {
            if (bitCnt[i]==0) continue;
            rs = rs + (1L << i);
        }
        return rs;
    }

}
