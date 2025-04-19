package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

/**
 * <a href="https://leetcode.com/problems/count-good-triplets-in-an-array">Leetcode 2179</a>
 */
public class CountGoodTripletsInAnArray {
    public static void main(String[] args) {
        new CountGoodTripletsInAnArray().run();
    }

    public void run() {
        int[] nums1;
        int[] nums2;

        nums1 = new int[]{2, 0, 1, 3};
        nums2 = new int[]{0, 1, 2, 3};
        Assertions.assertEquals(1, goodTriplets(nums1, nums2));
    }


    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] pos2 = new int[n], reversedIndexMapping = new int[n];
        for (int i = 0; i < n; i++) {
            pos2[nums2[i]] = i;
        }
        for (int i = 0; i < n; i++) {
            reversedIndexMapping[pos2[nums1[i]]] = i;
        }
        FenwickTree tree = new FenwickTree(n);
        long res = 0;
        for (int value = 0; value < n; value++) {
            int pos = reversedIndexMapping[value];
            int left = tree.query(pos);
            tree.update(pos, 1);
            int right = (n - 1 - pos) - (value - left);
            res += (long) left * right;
        }
        return res;
    }

    private static class FenwickTree {

        private int[] tree;

        public FenwickTree(int size) {
            tree = new int[size + 1];
        }

        public void update(int index, int delta) {
            index++;
            while (index < tree.length) {
                tree[index] += delta;
                index += index & -index;
            }
        }

        public int query(int index) {
            index++;
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }

    }
}
