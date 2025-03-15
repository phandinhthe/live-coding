package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.stream.IntStream;

public class HouseRobberIV {
    public static void main(String[] args) {
        new HouseRobberIV().run();
    }

    public void run() {
        int[] input = new int[]{2, 7, 9, 3, 1};
        int k = 2;
        int output = 2;
        Assertions.assertEquals(output, minCapability(input, k));

        input = new int[]{2, 3, 5, 9};
        k = 2;
        output = 5;
        Assertions.assertEquals(output, minCapability(input, k));

        input = new int[]{5038, 3053, 2825, 3638, 4648, 3259, 4948, 4248, 4940, 2834, 109, 5224, 5097, 4708, 2162, 3438, 4152, 4134, 551, 3961, 2294, 3961, 1327, 2395, 1002, 763, 4296, 3147, 5069, 2156, 572, 1261, 4272, 4158, 5186, 2543, 5055, 4735, 2325, 1206, 1019, 1257, 5048, 1563, 3507, 4269, 5328, 173, 5007, 2392, 967, 2768, 86, 3401, 3667, 4406, 4487, 876, 1530, 819, 1320, 883, 1101, 5317, 2305, 89, 788, 1603, 3456, 5221, 1910, 3343, 4597};
        k = 28;
        output = 4134;
        Assertions.assertEquals(output, minCapability(input, k));
    }

    int[] nums;

    public int minCapability(int[] nums, int k) {
        this.nums = nums;
        int l = IntStream.of(nums).min().getAsInt();
        int r = IntStream.of(nums).max().getAsInt();
        int res = l;
        while (l <= r) {
            int mid = (l + r) / 2;
            boolean isValid = isValid(mid, k); // count the element >= k, if count >= k => return valid.
            if (isValid) {
                res = mid;
                r = mid - 1;
                continue;
            }
            l = mid + 1;

        }

        return res;
    }

    boolean isValid(int candidate, int k) {
        int count = 0;
        int i = 0;
        while (i < nums.length && count < k) {
            if (nums[i] <= candidate) {
                count++;
                i += 2;
            } else {
                i++;
            }
        }
        return count==k;
    }
}
