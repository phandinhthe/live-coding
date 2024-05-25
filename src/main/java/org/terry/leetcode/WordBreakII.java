package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-break-ii
 *
 * 1. DFS
 * 2. From `startIndex = 0`, Looping over and append character[i] in `s`. => word = appended (startIndex, i). If word in `wordDict` => add to `sentence`
 * 3. Till the end of `s`, add `sentence` to `res`
 *
 * Complexity: O(n^n)
 */
public class WordBreakII {
	public static void main(String[] args) {
		new WordBreakII().test();
	}

	public void test() {
		String s;
		List<String> wordDict;
		List<String> output;
		List<String> actual;

		s = "catsanddog";
		wordDict = List.of("cat", "cats", "and", "sand", "dog");
		output = List.of("cats and dog", "cat sand dog");
		actual = wordBreak(s, wordDict);
		Assertions.assertEquals(new HashSet<>(output), new HashSet<>(actual));

		s = "pineapplepenapple";
		wordDict = List.of("apple", "pen", "applepen", "pine", "pineapple");
		output = List.of("pine apple pen apple", "pineapple pen apple", "pine applepen apple");
		actual = wordBreak(s, wordDict);
		Assertions.assertEquals(new HashSet<>(output), new HashSet<>(actual));

		s = "catsandog";
		wordDict = List.of("cats", "dog", "sand", "and", "cat");
		output = List.of();
		actual = wordBreak(s, wordDict);
		Assertions.assertEquals(new HashSet<>(output), new HashSet<>(actual));

		s = "c";
		wordDict = List.of("c");
		output = List.of("c");
		actual = wordBreak(s, wordDict);
		Assertions.assertEquals(new HashSet<>(output), new HashSet<>(actual));

	}

	private List<String> res;
	private int goalIndex;
	private String input;

	public List<String> wordBreak(String s, List<String> wordDict) {
		res = new ArrayList<>(wordDict.size());
		Set<String> set = new HashSet<>(wordDict);
		goalIndex = s.length();
		input = s;
		dfs(0, new StringBuilder(), set);
		return res;
	}

	void dfs(int startIndex, StringBuilder sentence, Set<String> set) {
		if (startIndex == goalIndex) {
			res.add(sentence.toString().trim());
		}

		int endIndex = startIndex;
		while (++endIndex <= input.length()) {
			String word = input.substring(startIndex, endIndex);
			if (!set.contains(word)) {
				continue;
			}

			sentence.append(word).append(" ");
			dfs(startIndex + word.length(), sentence, set);
			sentence.setLength(sentence.length() - word.length() - 1);
		}
	}
}
