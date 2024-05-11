package org.terry.algo.kmp;

// Research algorithm KMP - Knuth–Morris–Pratt Algorithm for Pattern Searching
// Reference: https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/

import java.util.Arrays;

/**
 * Input:  txt[] = "THIS IS A TEST TEXT", pat[] = "TEST" Output: Pattern found at index 10
 * <p>
 * Input:  txt[] =  "AABAACAADAABAABA" pat[] =  "AABA" Output: Pattern found at index 0, Pattern
 * found at index 9, Pattern found at index 12
 */

/**
 * The basic idea behind KMP’s algorithm is: whenever we detect a mismatch (after some matches), we
 * already know some of the characters in the text of the next window. We take advantage of this
 * information to avoid matching the characters that we know will anyway match.
 */
public class KMPBasicSample {

  public static void main(String[] args) {
    kmp("ABABDABACDABABCABAB", "ABABCABAB");
  }

  /**
   * - build LPS first - Use LPS to search `pattern` in `text`
   */
  public static void kmp(String text, String pattern) {
    int[] lps = lps(pattern);

    char[] textArr = text.toCharArray();
    char[] patternArr = pattern.toCharArray();

    int textSize = text.length();
    int patternSize = pattern.length();

    int i = 0; // for text.
    int j = 0; // for pattern.

    while (textSize - i >= patternSize - j) {
      if (j == patternSize) {
        System.out.println(i - patternSize);
        j = lps[j-1];
        continue;
      }
      if (textArr[i] == patternArr[j]) {
        i++;
        j++;
        continue;
      }

      if (j > 0) {
        j = lps[j-1];
        continue;
      }
      i ++;
    }
  }

  /**
   * Name lps indicates the longest proper prefix which is also a suffix. - A proper prefix is a
   * prefix with a whole string not allowed. - We search for lps in sub-patterns. More clearly we
   * focus on sub-strings of patterns that are both prefix and suffix. - For each sub-pattern
   * pat[0..i] where i = 0 to m-1, lps[i] stores the length of the maximum matching proper prefix
   * which is also a suffix of the sub-pattern pat[0..i]. - example, prefixes of "ABC" are "", "A",
   * "AB" and "ABC". Proper prefixes are "", "A" and "AB". Suffixes of the string are "", "C", "BC",
   * and "ABC".
   */
  public static int[] lps(String pattern) {
    int i = 1;
    int len = 0;
    char[] arr = pattern.toCharArray();
    int[] lps = new int[arr.length];

    // i always starts from 1 and len always starts from zero
    while (i < pattern.length() && len < pattern.length()) {
      if (arr[i] == arr[len]) {
        lps[len] = len;
        len++;

        lps[i] = len;
        i++;
        continue;
      }

      if (len > 0) {
        len = lps[len-1];
        continue;
      }

      i++;
    }
    return lps;
  }
}
