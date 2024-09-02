package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.stream.IntStream;

/**
 * Leetcode 1894: Find the Student that will replace the chalk.
 * Tuition:
 * - Calculate the sum of chalk.
 * - find the delta = k % sum
 * - iterate over the chalks and update delta -= chalk[i].
 * - return result `i` when chalk[i] > delta.
 */

public class FindTheStudentThatWillReplaceTheChalk {
    public static void main(String[] args) {
        new FindTheStudentThatWillReplaceTheChalk().run();
    }

    void run() {
        int[] chalk;
        int k;
        int output = 0;

        chalk = new int[]{5, 1, 5};
        k = 22;
        output = 0;
        Assertions.assertEquals(output, chalkReplacer(chalk, k));

        chalk = new int[]{3, 4, 1, 2};
        k = 25;
        output = 1;
        Assertions.assertEquals(output, chalkReplacer(chalk, k));

        chalk = new int[]{1, 1, 1};
        k = 2;
        output = 2;
        Assertions.assertEquals(output, chalkReplacer(chalk, k));

        chalk = new int[] {100000, 1898, 32924, 87, 1, 123, 8675, 199, 6, 10};
        k = 1_000_000_000;
        output = 0;
        Assertions.assertEquals(output, chalkReplacer(chalk, k));

        chalk = new int[] {1};
        k = 1_000_000_000;
        output = 0;
        Assertions.assertEquals(output, chalkReplacer(chalk, k));
    }

    public int chalkReplacer(int[] chalk, int k) {
        long sum = IntStream.of(chalk).asLongStream().sum();
        long delta = k % sum;
        int i = 0;

        while (delta >= chalk[i]) {
            delta -= chalk[i++];
            i %= chalk.length;
        }
        return i;
    }

}
