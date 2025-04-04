package org.terry.leetcode.hard;

import org.junit.jupiter.api.Assertions;

import java.util.*;

public class ApplyOperationsToMaximizeScore {
    public static void main(String[] args) {
        new ApplyOperationsToMaximizeScore().run();
    }

    void run() {
        // input
        List<Integer> nums;
        int k;

        nums = List.of(8, 3, 9, 3, 8);
        k = 2;
        Assertions.assertEquals(81, maximumScore(nums, k));

        nums = List.of(19, 12, 14, 6, 10, 18);
        k = 3;
        Assertions.assertEquals(4788, maximumScore(nums, k));
    }

    private final int MOD = 1_000_000_000 + 7;

    public int maximumScore(List<Integer> nums, int k) {
        int[] primeScores = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            primeScores[i] = scoreOf(nums.get(i));
        }
        long res = 1L;

        // Monolith stack
        Deque<Integer> stack = new ArrayDeque<>();
        int[] left = new int[nums.size()];
        int[] right = new int[nums.size()];
        Arrays.fill(left, -1);
        Arrays.fill(right, nums.size());
        for (int i = 0; i < nums.size(); i++) {
            while (!stack.isEmpty() && primeScores[stack.peek()] < primeScores[i]) {
                right[stack.pop()] = i;
            }
            if (!stack.isEmpty()) {
                left[i] = stack.peek();
            }
            stack.push(i);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int rs = nums.get(b) - nums.get(a);
            if (rs!=0)
                return rs;
            rs = a - b;
            return rs;
        });

        for (int i = 0; i < nums.size(); i++) {
            pq.offer(i);
        }

        while (k > 0) {
            int cur = pq.poll();
            long lefCnt = cur - left[cur];
            long rightCnt = right[cur] - cur;
            long min = (long) Math.min(rightCnt * lefCnt, k);
            k -= min;
            res = res * power(nums.get(cur), min) % MOD;
        }
        return (int) res;
    }

    long power(long base, long exponent) {
        long res = 1;

        while (exponent > 0) {
            if (exponent % 2==1) {
                res = (res * base) % MOD;
            }

            base = (base * base) % MOD;
            exponent /= 2;
        }
        return res;
    }

    int scoreOf(int num) {
        int score = 0;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i!=0)
                continue;
            // i is a prime factor
            score++;
            while (num % i==0) {
                num /= i;
            }
        }
        if (num >= 2)
            score++;
        return score;
    }
}
