package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.function.BiFunction;

/**
 * <a href="https://leetcode.com/problems/count-good-numbers/description/?envType=daily-question&envId=2025-04-13">Leetcode-1922</a>
 */
public class CountGoodNumbers {
    public static void main(String[] args) {
        new CountGoodNumbers().run();
    }

    private void run() {
        Assertions.assertEquals(643535977, countGoodNumbers(806166225460393L));
        Assertions.assertEquals(5, countGoodNumbers(1));
        Assertions.assertEquals(400, countGoodNumbers(4));
        Assertions.assertEquals(564908303, countGoodNumbers(50));
    }

    public int countGoodNumbers(long length) {
        final int MOD = 1_000_000_007;
        long oddLength = length / 2;
        long evenLength = (length + 1) / 2;

        int primeCnt = 4;// {2,3,5,7};
        int evenCnt = 5;// {0,2,4,6,8};

        BiFunction<Long, Long, Long> pow = (x, y) -> {
            long res = 1L;
            while (y > 0) {
                if ((y & 1)==1) {
                    res = (x * res) % MOD;
                }
                x *= x;
                x %= MOD;
                y >>= 1;
            }
            return res;
        };

        return (int) (pow.apply((long) evenCnt, evenLength) * pow.apply((long) primeCnt, oddLength) % MOD);
    }

}
