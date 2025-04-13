package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindTheCountOfGoodIntegers {
    public static void main(String[] args) {
        new FindTheCountOfGoodIntegers().run();
    }

    public void run() {

        Assertions.assertEquals(
            33, countGoodIntegers(3, 7)
        );

        Assertions.assertEquals(
            252, countGoodIntegers(4, 1)
        );

        Assertions.assertEquals(
            27L, countGoodIntegers(3, 5)
        );

        Assertions.assertEquals(
            2, countGoodIntegers(1, 4)
        );

        Assertions.assertEquals(
            2468, countGoodIntegers(5, 6)
        );

    }
//    public long countGoodIntegers(int n, int k) {
//        Set<String> dict = new HashSet<>();
//        int base = (int) Math.pow(10, (n - 1) / 2);
//        int skip = n & 1;
//        /* Enumerate the number of palindrome numbers of n digits */
//        for (int i = base; i < base * 10; i++) {
//            String s = Integer.toString(i);
//            s += new StringBuilder(s).reverse().substring(skip);
//            long palindromicInteger = Long.parseLong(s);
//            /* If the current palindrome number is a k-palindromic integer */
//            if (palindromicInteger % k == 0) {
//                char[] chars = s.toCharArray();
//                Arrays.sort(chars);
//                dict.add(new String(chars));
//            }
//        }
//        System.out.println(dict);
//        long[] factorial = new long[n + 1];
//        factorial[0] = 1;
//        for (int i = 1; i <= n; i++) {
//            factorial[i] = factorial[i - 1] * i;
//        }
//        long ans = 0;
//        for (String s : dict) {
//            int[] cnt = new int[10];
//            for (char c : s.toCharArray()) {
//                cnt[c - '0']++;
//            }
//            /* Calculate permutations and combinations */
//            long tot = (n - cnt[0]) * factorial[n - 1];
//            for (int x : cnt) {
//                tot /= factorial[x];
//            }
//            ans += tot;
//        }
//
//        return ans;
//    }

    //[055, 155, 255, 355, 455, 555, 556, 557, 558, 559]

    Set<String> visited;

    public long countGoodIntegers(int n, int k) {
        long ans = 0L;
        long base = (long) Math.pow(10, (n - 1) / 2);
        // with even number this value will be zero, so when we get `palindrome` we will get from index zero.
        // with odd nubmer, this value will be 1, so when we get `palindrome` we will get from index 1
        int skip = n & 1;
        visited = new HashSet<>();
        for (long i = base; i < base * 10; i++) {
            String left = String.valueOf(i);
            String right = new StringBuilder(left).reverse().substring(skip);
            long palindrome = Long.parseLong(left.concat(right));
            if (palindrome % k!=0) continue;
            ans += countValid(palindrome);
        }
        return ans;
    }

    long countValid(long num) {
        long m = num;
        int[] freq = new int[10];
        int cnt = 0;
        while (num!=0) {
            int n = (int) (num % 10);
            freq[n]++;
            cnt++;
            num /= 10;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(freq[i]);
        }
        if (visited.contains(sb.toString())) return 0;

        visited.add(sb.toString());
        long countPerm = countPerm(freq, cnt);
        long countLeadingZeroPerm = countLeadingZeroPerm(freq, cnt);

        System.out.printf("num %d\n", m);
        System.out.printf("countPerm %d - countLeadingZeroPerm %d = %d\n", countPerm, countLeadingZeroPerm,
            countPerm - countLeadingZeroPerm);

        return countPerm - countLeadingZeroPerm;
    }

    long countPerm(int[] freq, int total) {
        long totalFractorial = fractorial(total);
        for (int i = 0; i < 10; i++) {
            if (freq[i] <= 1) continue;
            totalFractorial /= fractorial(freq[i]);
        }
        return totalFractorial;
    }

    long countLeadingZeroPerm(int[] freq, int total) {
        if (freq[0]==0) return 0;
        freq[0]--;
        long totalPerm = fractorial(total - 1);
        for (int i = 0; i < 10; i++) {
            if (freq[i] <= 1) continue;
            totalPerm /= fractorial(freq[i]);
        }
        return totalPerm;
    }

    long fractorial(long n) {
        int i = 1;
        int product = 1;
        while (i <= n) {
            product *= i;
            i++;
        }
        return product;
    }

}
