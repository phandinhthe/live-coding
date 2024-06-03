package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

/**
 * https://leetcode.com/problems/append-characters-to-string-to-make-subsequence
 * 2486. Append Characters to String to Make Subsequence
 * <p>
 * - use 2 pointers
 * - if s[i] == t[j], increment j
 * - value of t.length - j will the result.
 */
public class AppendCharactersToStringToMakeSubsequence {
	public static void main(String[] args) {
		new AppendCharactersToStringToMakeSubsequence().test();
	}

	public void test() {
		String s;
		String t;
		int output;
		int actual;

		s = "coaching";
		t = "coding";
		output = 4;
		actual = appendCharacters(s, t);
		Assertions.assertEquals(output, actual);

		s = "abcdea";
		t = "a";
		output = 0;
		actual = appendCharacters(s, t);
		Assertions.assertEquals(output, actual);

		s = "z";
		t = "abcde";
		output = 5;
		actual = appendCharacters(s, t);
		Assertions.assertEquals(output, actual);
	}

	public int appendCharacters(String s, String t) {
		char[] sa = s.toCharArray();
		char[] ta = t.toCharArray();
		int j = 0;

		for (int i = 0; i < sa.length; i++) {
			if (sa[i] == ta[j]) j++; // char t[j] is in sa
			if (j == ta.length) return 0; // if all string `t` is in `s`, return value `0`
		}
		return ta.length - j;
	}

}
