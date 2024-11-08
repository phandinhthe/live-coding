package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/maximum-xor-for-each-query/description/">
 * 1829. Maximum XOR fore Each query
 * </a>
 */
public class MaximumXorForEachQuery {
    public static void main(String[] args) {
        new MaximumXorForEachQuery().run();
    }

    public void run() {
        int[] input;
        int maximumBit;
        int[] output;

        input = new int[]{0, 1, 1, 3};
        maximumBit = 2;
        output = new int[]{0, 3, 2, 3};
        Assertions.assertArrayEquals(getMaximumXor(input, maximumBit), output);

        input = new int[]{2, 3, 4, 7};
        maximumBit = 3;
        output = new int[]{5, 2, 6, 5};
        Assertions.assertArrayEquals(getMaximumXor(input, maximumBit), output);

        input = new int[]{0,1,2,2,5,7};
        maximumBit = 3;
        output = new int[]{4, 3, 6, 4, 6, 7};
        Assertions.assertArrayEquals(getMaximumXor(input, maximumBit), output);
    }

    public int[] getMaximumXor(int[] nums, int maximumBit) {
        List<Integer> rs = new ArrayList<>();
        int xor = 0;
        int max = (1 << maximumBit) - 1;
        for (int n : nums) {
            xor ^= n;
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            rs.add((max ^ xor));
            xor ^= nums[i];
        }
        return rs.stream().mapToInt(Integer::intValue).toArray();
    }
}
