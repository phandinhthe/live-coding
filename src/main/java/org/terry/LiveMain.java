package org.terry;

import java.util.ArrayList;
import java.util.List;

public class LiveMain {
	public static void main(String[] args) {
		new LiveMain().test();
	}

	public void test() {
		System.out.println(partition("aab"));
	}

	public List<List<String>> partition(String s) {
		this.s = s;
		this.rs = new ArrayList<>();
		this.palindrome = new boolean[s.length()][s.length()];
		backtrack(0, new ArrayList<>());
		return rs;
	}

	String s;
	List<List<String>> rs;
	boolean[][] palindrome;

	void backtrack(int idx, List<String> subRes) {
		if (idx == s.length()) {
			rs.add(subRes);
			return;
		}

		for (int len = idx + 1; len <= s.length(); len++) {
			String subString = s.substring(idx, len);
			if (palindrome[idx][len-1] || isPalindrome(subString)) {
				palindrome[idx][len-1] = true;
				subRes.add(subString);
				backtrack(len, new ArrayList<>(subRes));
				subRes.removeLast();
			}
		}
	}

	boolean isPalindrome(String str) {
		int start = 0;
		int end = str.length() - 1;
		while (start < end) {
			if (str.charAt(start) != str.charAt(end)) return false;
			start++;
			end--;
		}
		return true;
	}
}