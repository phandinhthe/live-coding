package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

//https://leetcode.com/problems/replace-words/
// Replace words
// Notice:
// - Use trie
// - Complexity (n*m) : `n` is the count of elements, `m` is the length of the element.

public class ReplaceWords {
	public static void main(String[] args) {
		new ReplaceWords().test();
	}

	public void test() {
		List<String> dictionary;
		String sentence;
		String output;
		String actual;

		dictionary = List.of("cat", "bat", "rat");
		sentence = "the cattle was rattled by the battery";
		actual = "the cat was rat by the bat";
		output = replaceWords(new ArrayList<>(dictionary), sentence);
		Assertions.assertEquals(output, actual);

		dictionary = List.of("a", "b", "c");
		sentence = "aadsfasf absbs bbab cadsfafs";
		actual = "a a b c";
		output = replaceWords(new ArrayList<>(dictionary), sentence);
		Assertions.assertEquals(output, actual);

	}

	public String replaceWords(List<String> dictionary, String sentence) {
		Trie trie = new Trie();
		for (String s : dictionary) {
			trie.insert(s);
		}

		StringJoiner joiner = new StringJoiner(" ");
		for (String word : sentence.split(" ")) {
			String prefix = trie.prefix(word);
			if (prefix.isEmpty())
				prefix = word;
			joiner.add(prefix);
		}
		return joiner.toString();
	}

	static class Trie {
		Trie[] children = new Trie[26];
		boolean isWord;

		void insert(String s) {
			char[] array = s.toCharArray();
			Trie root = this;
			for (char c : array) {
				int index = c - 'a';
				if (root.children[index] == null) {
					root.children[index] = new Trie();
				}
				root = root.children[index];
			}
			root.isWord = true;
		}

		String prefix(String s) {
			StringBuilder rs = new StringBuilder();
			Trie root = this;
			for (char c : s.toCharArray()) {
				int index = c - 'a';
				if (root.children[index] == null)
					break;
				rs.append(c);
				root = root.children[index];
				if (root.isWord) return rs.toString();
			}
			return "";
		}
	}
}
