package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * Maximum score words formed by letters
 * https://leetcode.com/problems/maximum-score-words-formed-by-letters
 */
public class MaximumScoreWordsFormedByLetters {
	public static void main(String[] args) {
		new MaximumScoreWordsFormedByLetters().test();
	}

	public void test() {
		String[] words;
		char[] letters;
		int[] score;
		int actual, output;

		words = new String[]{"dog", "cat", "dad", "good"};
		letters = new char[]{'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o'};
		score = new int[]{1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		actual = maxScoreWords(words, letters, score);
		output = 23;
		Assertions.assertEquals(output, actual);

		words = new String[]{"xxxz", "ax", "bx", "cx"};
		letters = new char[]{'z', 'a', 'b', 'c', 'x', 'x', 'x'};
		score = new int[]{4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 10};
		actual = maxScoreWords(words, letters, score);
		output = 27;
		Assertions.assertEquals(output, actual);

		words = new String[]{"leetcode"};
		letters = new char[]{'l', 'e', 't', 'c', 'o', 'd'};
		score = new int[]{0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0};
		actual = maxScoreWords(words, letters, score);
		output = 0;
		Assertions.assertEquals(output, actual);

	}

	private int[] scores;
	private int[] letters;

	public int maxScoreWords(String[] words, char[] letters, int[] score) {
		this.scores = score;
		this.letters = new int[26];
		for (char l : letters) {
			this.letters[l - 'a']++;
		}
		return dfs(new WordTrack(), 0, words);
	}

	private int dfs(WordTrack track, int idx, String[] words) {
		if (idx == words.length) {
			return track.score;
		}
		String curWord = words[idx];
		int res = 0;
		if (track.isValid(curWord)) {
			track.add(curWord);
			res = dfs(track, idx + 1, words);
			track.remove(curWord);
		}
		res = Math.max(res, dfs(track, idx + 1, words));
		return res;
	}

	private class WordTrack {
		int[] wLetters;
		int score;

		public WordTrack() {
			wLetters = Arrays.copyOf(letters, letters.length);
		}


		boolean isValid(String word) {
			int[] arr = new int[26];
			for (char c : word.toCharArray()) {
				arr[c - 'a']++;
			}
			return isValid(arr);
		}

		boolean isValid(int[] letters) {
			for (int i = 0; i < this.wLetters.length; i++) {
				if (this.wLetters[i] - letters[i] < 0) return false;
			}
			return true;
		}

		void add(String word) {
			for (int i = 0; i < word.length(); i++) {
				int index = word.charAt(i) - 'a';
				wLetters[index]--;
				this.score += scores[index];
			}
		}

		void remove(String word) {
			for (int i = 0; i < word.length(); i++) {
				int index = word.charAt(i) - 'a';
				wLetters[index]++;
				this.score -= scores[index];
			}
		}
	}
}
