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
        // input:
        int n;
        int output;

        // assertion
        n = 5;
        output = 5;
        Assertions.assertEquals(output, smallestValue(n));
        n = 3;
        output = 3;
        Assertions.assertEquals(output, smallestValue(n));
        n = 4;
        output = 4;
        Assertions.assertEquals(output, smallestValue(n));
        n = 7;
        output = 7;
        Assertions.assertEquals(output, smallestValue(n));
        n = 14;
        output = 5;
        Assertions.assertEquals(output, smallestValue(n));
        n = 15;
        output = 5;
        Assertions.assertEquals(output, smallestValue(n));

    }

    int findSum(int n) {
        int res = 0;
        int prime = 2;
        while (n > 1) {
            while (n % prime==0) {
                n /= prime;
                res += prime;
            }
            prime++;
        }
        return res;
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
}


