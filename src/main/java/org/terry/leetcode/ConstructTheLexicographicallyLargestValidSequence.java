package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

public class ConstructTheLexicographicallyLargestValidSequence {
    public static void main(String[] args) {
        new ConstructTheLexicographicallyLargestValidSequence().run();
    }

    public void run() {
        Assertions.assertArrayEquals(new int[]{3, 1, 2, 3, 2}, constructDistancedSequence(3));
        Assertions.assertArrayEquals(new int[]{4, 2, 3, 2, 4, 3, 1}, constructDistancedSequence(4));
        Assertions.assertArrayEquals(new int[]{5, 3, 1, 4, 3, 5, 2, 4, 2}, constructDistancedSequence(5));
        Assertions.assertArrayEquals(new int[]{6, 4, 2, 5, 2, 4, 6, 3, 5, 1, 3}, constructDistancedSequence(6));
        Assertions.assertArrayEquals(new int[]{7, 5, 3, 6, 4, 3, 5, 7, 4, 6, 2, 1, 2}, constructDistancedSequence(7));
    }

    public int[] constructDistancedSequence(int n) {
        int limit = n * 2 - 1;
        int[] res = new int[limit];
        boolean[] visited = new boolean[n + 1];
        backtrack(res, n, 0, visited, limit);
        return res;
    }

    private boolean backtrack(int[] nums, int num, int index, boolean[] visited, int limit) {
        if (index==limit) return true;
        if (nums[index]!=0) {
            return backtrack(nums, num, index + 1, visited, limit);
        }
        for (int n = num; n >= 1; n--) {
            // validation
            if (visited[n]) continue;
            int nextIndex = (n > 1) ? n + index:index;

            if (nextIndex >= limit) continue;
            if (nums[index]!=0 || nums[nextIndex]!=0) continue;

            // track before go to the next step
            visited[n] = true;
            nums[index] = n;
            nums[nextIndex] = n;
            if (backtrack(nums, num, index + 1, visited, limit)) return true;

            // reset, if the decision is not valid
            visited[n] = false;
            nums[index] = 0;
            nums[nextIndex] = 0;
        }

        return false;
    }

}
