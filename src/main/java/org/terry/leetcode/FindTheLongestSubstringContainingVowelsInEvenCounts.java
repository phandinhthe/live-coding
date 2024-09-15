package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

/**
 * <a herf='https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts'>
 * Leetcode 1371: Find the longest substring containing vowels in even counts
 * </a>
 * Use bitmask to record the state
 * with vowels (a, e , i, o, u) -> (1, 2, 4, 8, 16) -> 00000
 * sample: 01000 -> there is one odd count of `e`.
 * map as `prefix` to record the first appearance of a state.
 * if (bitmask == 0) res = index + 1; // special case
 * else if (prefix contains `bitmask`) res = Max(res, index - prefix.get(bitmask))
 * prefix.put(bitmask, index) if `bitmask` is absent
 * <p>
 * Complexity: O(n)
 * Space: O(n)
 **/

public class FindTheLongestSubstringContainingVowelsInEvenCounts {
    public static void main(String[] args) {
        new FindTheLongestSubstringContainingVowelsInEvenCounts().run();
    }


    public void run() {
        String s;
        int expected;

        s = "eleetminicoworoep";
        expected = 13;
        Assertions.assertEquals(expected, findTheLongestSubstring(s));

        s = "leetcodeisgreat";
        expected = 5;
        Assertions.assertEquals(expected, findTheLongestSubstring(s));

        s = "bcbcbc";
        expected = 6;
        Assertions.assertEquals(expected, findTheLongestSubstring(s));
    }

    public int findTheLongestSubstring(String s) {
        // map (bitmask, index)
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        int bitmask = 0;
        char[] arr = s.toCharArray();
        for (int index = 0; index < s.length(); index++) {
            int value = toVowels(arr[index]);
            bitmask = bitmask ^ value;
            if (bitmask==0) res = index + 1;
            if (map.containsKey(bitmask)) {
                res = Math.max(res, index - map.get(bitmask));
            }
            map.putIfAbsent(bitmask, index);
        }

        return res;
    }

    final int toVowels(char c) {
        return switch (c) {
            case 'a' -> 1;
            case 'e' -> 2;
            case 'i' -> 4;
            case 'o' -> 8;
            case 'u' -> 16;
            default -> 0;
        };
    }
}
