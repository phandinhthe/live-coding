package org.terry;

import org.junit.jupiter.api.Assertions;

public class LiveMain {
    public static void main(String[] args) {
        new LiveMain().test();
    }
    public void test() {
        int[] nums;
        int n;
        int output, actual;

        nums = new int[] {1, 3};
        n = 6;
        output = 1;
        actual = minPatches(nums, n);
        Assertions.assertEquals(output, actual);

        nums = new int[] {1, 5, 10};
        n = 20;
        output = 2;
        actual = minPatches(nums, n);
        Assertions.assertEquals(output, actual);

	}
    public int minPatches(int[] nums, int n) {
        long miss = 0L;
        int i = 0;
        int res = 0;
        while (miss < n) {
            if (i < nums.length && miss + 1 >= nums[i]) {
                miss += (nums[i]);
                i++;
            } else {
                miss += (miss+1);
                res++;
            }
        }

        return res;
    }
}
