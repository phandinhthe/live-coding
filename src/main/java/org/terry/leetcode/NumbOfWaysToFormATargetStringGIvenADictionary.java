package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

/**
 * <a href="https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary">
 *     Leetcode 1639. Number of ways to form a target string given a dictionary.
 # </a>
 */
public class NumbOfWaysToFormATargetStringGIvenADictionary {
    public static void main(String[] args) {
        new NumbOfWaysToFormATargetStringGIvenADictionary().run();
    }

    public void run() {
        String[] words;
        String target;

        words = new String[]{"acca", "bbbb", "caca"};
        target = "aba";
        Assertions.assertEquals(6, numWays(words, target));

        words = new String[]{"abba", "baab"};
        target = "bab";
        Assertions.assertEquals(4, numWays(words, target));

        words = new String[]{"cbabddddbc", "addbaacbbd", "cccbacdccd", "cdcaccacac", "dddbacabbd", "bdbdadbccb", "ddadbacddd", "bbccdddadd", "dcabaccbbd", "ddddcddadc", "bdcaaaabdd", "adacdcdcdd", "cbaaadbdbb", "bccbabcbab", "accbdccadd", "dcccaaddbc", "cccccacabd", "acacdbcbbc", "dbbdbaccca", "bdbddbddda", "daabadbacb", "baccdbaada", "ccbabaabcb", "dcaabccbbb", "bcadddaacc", "acddbbdccb", "adbddbadab", "dbbcdcbcdd", "ddbabbadbb", "bccbcbbbab", "dabbbdbbcb", "dacdabadbb", "addcbbabab", "bcbbccadda", "abbcacadac", "ccdadcaada", "bcacdbccdb"};
        target = "bcbbcccc";
        Assertions.assertEquals(677452090, numWays(words, target));
    }

    private static final int MOD = 1_000_000_000 + 7;
    private int[][] freq;
    private int[][] cache;
    public int numWays(String[] words, String target) {
        this.freq = preprocess(words);
        this.cache = new int[words[0].length()][target.length()];
        return count(words, target, 0, 0);
    }

    /*
    Recursion to count the ways to form target.
    include = freq[wordIndex][targetIndex char index] + count(words, target, wordIndex + 1, targetIndex + 1)
    skip = count(words, target, wordIndex + 1, target)
    return include + skip
     */
    public int count(String[] words, String target, int wordIndex, int targetIndex) {
        if (targetIndex==target.length()) return 1;

        if (words[0].length() - wordIndex < target.length() - targetIndex) {
            return 0;
        }

        if (cache[wordIndex][targetIndex] != 0) {
          return cache[wordIndex][targetIndex];
        }

        int targetCharIndex = target.charAt(targetIndex) - 'a';
        long res = (long) freq[wordIndex][targetCharIndex] * count(words, target, wordIndex + 1, targetIndex + 1);
        res += count(words, target, wordIndex + 1, targetIndex);
        cache[wordIndex][targetIndex] = (int)(res % MOD);
        return cache[wordIndex][targetIndex];
    }

    /* preprocess to calculate the char frequency of words.
     * charFrequency[i][j], i index in 'word', j of char in alphabet (26).
     */
    public int[][] preprocess(String[] words) {
        int[][] freq = new int[words[0].length()][26];
        for (String word : words) {
            for (int w = 0; w < word.length(); w++) {
                freq[w][word.charAt(w) - 'a']++;
            }
        }
        return freq;
    }
}