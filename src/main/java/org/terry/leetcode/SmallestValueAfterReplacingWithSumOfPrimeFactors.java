package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

/**
 * Leetcode 2507: Smalles value after replacing with sum of prime factors
 * <p>
 * Tuition:
 * - res = findSum(n) -> calculate the sum of prime factors of n
 * - if: res == n => return `res`
 * - else: continue with n = `res`
 * <p>
 * Complexity: O(nlogn)
 * Space: O(1)
 */
public class SmallestValueAfterReplacingWithSumOfPrimeFactors {
    public static void main(String[] args) {
        new SmallestValueAfterReplacingWithSumOfPrimeFactors().run();
    }

    public void run() {
        // assertion
        assertEqual(5, 5);
        assertEqual(3, 3);
        assertEqual(4, 4);
        assertEqual(7, 7);
        assertEqual(5, 14);
        assertEqual(5, 15);
    }
    public void assertEqual(int output, int n) {
        Assertions.assertEquals(output, smallestValue(n));
    }
    public int smallestValue(int n) {
        int res = 0;
        boolean hasFactor = true;
        while (hasFactor) {
            res = findSum(n);
            hasFactor = (res!=n);
            n = res;
        }
        return n;
    }
    private int findSum(int n) {
        int res = 0;
        int prime = 2;
        while (n > 1) {
            if (n % prime!=0) {
                prime++;
                continue;
            }
            n /= prime;
            res += prime;
        }
        return res;
    }

}


