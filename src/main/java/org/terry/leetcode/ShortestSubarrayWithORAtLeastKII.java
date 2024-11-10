package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

/**
 * <a href="https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii">
 * Shortest subarray iwth or at least k II</a>
 * <p>
 * - Use `bits[]` as the array recording the bit set of the current total OR result. Bits ' every value is the size of bit `1`.
 * - function or: bitwise operation OR (bits, number n)
 * - function remove: bitwise operation remove `n` out of `bits`
 * - function toDecimal: convert bits to number as decimal.
 * <p>
 * - use 2 pointers:
 * ++ right move forwards, OR (bits, nums[right])
 * ++ in every step of right moving forward
 * +++ remove(bits, nums[left])  while ( toDecimal(bits) >= k and left <= right)
 * +++ update size = min(size, right - left + 1)
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

        nums = new int[]{1, 2};
        k = 0;
        Assertions.assertEquals(1, minimumSubarrayLength(nums, k));

        nums = new int[]{1, 2, 32, 21};
        k = 55;
        Assertions.assertEquals(3, minimumSubarrayLength(nums, k));

        nums = new int[]{1, 52, 50, 12, 53, 50, 54, 2};
        k = 55;
        Assertions.assertEquals(2, minimumSubarrayLength(nums, k));
    }

    public int minimumSubarrayLength(int[] nums, int k) {
        // record the bit set number, every value is the number of bit ONE
        int[] bits = new int[32];

        int r = 0;
        int l = 0;
        final int MAX = 2 * 100_000;
        int size = MAX;

        for (; r < nums.length; r++) {
            bits = or(bits, nums[r]);
            while (l <= r && toDecimal(bits) >= k) {
                size = Math.min(size, r - l + 1);
                remove(bits, nums[l]);
                l++;
            }
        }

        return MAX==size ? -1:size;
    }

    private int[] or(int[] bits, int n) {
        int i = 0;
        while (n > 0) {
            if (n % 2==1) bits[i]++;
            n /= 2;
            i++;
        }
        return bits;
    }

    // remove number n out of bits
    private int[] remove(int[] bits, int n) {
        int i = 0;
        while (n > 0) {
            if (n % 2==1 && bits[i] > 0) bits[i]--;
            n /= 2;
            i++;
        }
        return bits;
    }

    // Convert from bits[] to decimal number.
    private int toDecimal(int[] bits) {
        int rs = 0;

        for (int i = 0; i < bits.length; i++) {
            if (bits[i] > 0) {
                rs = rs + (1 << i);
            }
        }
        return rs;
    }
}
