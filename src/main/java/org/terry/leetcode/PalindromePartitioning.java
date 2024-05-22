package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 131. Palindrome Partitioning
 * https://leetcode.com/problems/palindrome-partitioning
 *
 * isPalindrome-> complexity(n)
 * patition -> complexity (n^n)
 * total: O(n^n*n)
 */
public class PalindromePartitioning {
	public static void main(String[] args) {
		new PalindromePartitioning().test();
	}

	public void test() {
		String s;
		List<List<String>> output;
		List<List<String>> actual;

		s = "aab";
		output = Arrays.stream(new String[][]{{"a", "a", "b"}, {"aa", "b"}}).map(List::of).toList();
		actual = partition(s);
		Assertions.assertEquals(output, actual);

		s = "a";
		output = Arrays.stream(new String[][]{{"a"}}).map(List::of).toList();
		actual = partition(s);
		Assertions.assertEquals(output, actual);

		s = "aaaa";
		output = Arrays.stream(new String[][]{
						{"a", "a", "a", "a"}, {"a", "a", "aa"}, {"a", "aa", "a"}, {"a", "aaa"},
						{"aa", "a", "a"}, {"aa", "aa"}, {"aaa", "a"}, {"aaaa"}})
				.map(List::of).toList();
		actual = partition(s);
		Assertions.assertEquals(output, actual);

	}

	private String s;
	private List<List<String>> rs;
	private boolean[][] palindrome;

	public List<List<String>> partition(String s) {
		this.s = s;
		this.rs = new ArrayList<>();
		this.palindrome = new boolean[s.length()][s.length()];
		backtrack(0, new ArrayList<>());
		return rs;
	}

	void backtrack(int idx, List<String> subRes) {
		if (idx == s.length()) {
			rs.add(subRes);
			return;
		}

		for (int len = idx + 1; len <= s.length(); len++) {
			String subString = s.substring(idx, len);
			if (palindrome[idx][len - 1] || isPalindrome(idx, len - 1)) {
				palindrome[idx][len - 1] = true;
				subRes.add(subString);
				backtrack(len, new ArrayList<>(subRes));
				subRes.removeLast();
			}
		}
	}

	boolean isPalindrome(int left, int right) {
		while (left < right) {
			if (s.charAt(left) != s.charAt(right)) return false;
			left++;
			right--;
		}
		return true;
	}

}
