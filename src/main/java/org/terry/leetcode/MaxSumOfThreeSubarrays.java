package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/description/?envType=daily-question&envId=2024-12-28">
 * Maximum Sum of 3 non-overlapping subarrays.
 * </a>
 * <p>
 * Tuition:
 * - Find the sum of k numbers in sequence start from 0. => sum[]
 * - Create function `maxSum`:
 * - + curSum = sum[i]  + maxSum(sum, i + k, cnt + 1)
 * - + nextSum = maxSum(sum, i + 1, cnt) // cover case i + 1.
 * - + We choose the Max(curSum, nextSum) -> appending to cache[i][cnt].
 * - Check:
 * - + the result from index i, next is index i+k, next is index i+2k => curSum
 * - + Cover case i+1 also. => nextSum
 * - + choose the Max (curSum, nextSum) for the result appending.
 **/
public class MaxSumOfThreeSubarrays {
    public static void main(String[] args) {
        new MaxSumOfThreeSubarrays().run();
    }

    private void run() {
        int[] nums;
        int k;

        nums = new int[]{1, 2, 1, 2, 6, 7, 5, 1};
        k = 2;
        Assertions.assertArrayEquals(new int[]{0, 3, 5}, maxSumOfThreeSubarrays(nums, k));

        nums = new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1};
        k = 2;
        Assertions.assertArrayEquals(new int[]{0, 2, 4}, maxSumOfThreeSubarrays(nums, k));
    }

    int[][] cache;
    int k;

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        this.cache = new int[nums.length][3];
        this.k = k;
        int[] sum = new int[nums.length];
        int l = 0;
        int window = 0;
        for (int r = 0; r < nums.length; r++) {
            window += nums[r];
            if (r - l + 1 < k) {
                continue;
            }

            sum[l] = window;
            window -= nums[l];
            l++;
        }
        return indices(sum).stream().mapToInt(Integer::intValue).toArray();
    }

    List<Integer> indices(int[] sum) {
        int i = 0;
        List<Integer> res = new ArrayList<>();
        while (i <= sum.length - k && res.size() < 3) {
            int curSum = sum[i] + maxSum(sum, i + k, res.size() + 1);
            int nextSum = maxSum(sum, i + 1, res.size());

            if (curSum >= nextSum) {
                res.add(i);
                i += k;
                continue;
            }
            i++;
        }
        return res;
    }

    int maxSum(int[] sum, int cur, int cnt) {
        if (cnt==3 || cur > sum.length - k) {
            return 0;
        }

        if (cache[cur][cnt]!=0) return cache[cur][cnt];
        int curSum = sum[cur] + maxSum(sum, cur + k, cnt + 1);
        int nxtSum = maxSum(sum, cur + 1, cnt);

        cache[cur][cnt] = Math.max(curSum, nxtSum);
        return cache[cur][cnt];
    }
}
