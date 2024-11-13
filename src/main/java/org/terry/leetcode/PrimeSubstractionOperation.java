package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.stream.IntStream;

/**
 * <a href="https://leetcode.com/problems/prime-subtraction-operation/description/">Leetcode 2601: Prime subtraction Operation</a>
 */
public class PrimeSubstractionOperation {
    public static void main(String[] args) {
        new PrimeSubstractionOperation().run();
    }

    public void run() {
        // input
        int[] nums;
        boolean expected;

        nums = new int[]{4, 9, 6, 10};
        expected = true;
        Assertions.assertEquals(expected, primeSubOperation(nums));

        nums = new int[]{6, 8, 11, 12};
        expected = true;
        Assertions.assertEquals(expected, primeSubOperation(nums));

        nums = new int[]{5, 8, 3};
        expected = false;
        Assertions.assertEquals(expected, primeSubOperation(nums));

        nums = new int[]{18, 12, 14, 6};
        expected = false;
        Assertions.assertEquals(expected, primeSubOperation(nums));

        nums = new int[]{3, 4, 10, 15, 6};
        expected = true;
        Assertions.assertEquals(expected, primeSubOperation(nums));
    }

    public boolean primeSubOperation(int[] nums) {
        int prev = 0;
        int[] primes = primes(IntStream.of(nums).max().getAsInt());
        for (int i = 0; i < nums.length; i++) {
            int upper = nums[i] - prev;
            if (upper <= 0)
                return false;
            int prime = primes[upper - 1];
            if (nums[i] - prime <= prev)
                return false;
            prev = nums[i] - prime;
        }
        return true;
    }

    private int[] primes(int max) {
        int[] primes = new int[max];
        for (int i = 2; i < max; i++) {
            if (isPrime(i)) {
                primes[i] = i;
            } else
                primes[i] = primes[i - 1];
        }
        return primes;
    }

    private boolean isPrime(int n) {
        for (int i = 2; i < (int)(Math.sqrt(n) + 1); i++) {
            if (n % i==0)
                return false;
        }

        return true;
    }
}
