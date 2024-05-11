package org.terry.datastructure.trie;

import java.util.LinkedList;
import java.util.List;

public class Trie {

	public static void main(String[] args) {
//		TrieNode trieNode = new TrieNode();
//		String s = "theisthebestlovelovenatty";
//		for (int i = 0; i < s.length(); i ++) {
//			trieNode.insert(s.substring(i));
//		}
//		test(trieNode);

		testSuffix();
	}

	static void test(TrieNode trieNode) {
		assert trieNode.search("the");
		assert trieNode.search("is");
		assert trieNode.search("the");
		assert trieNode.search("best");
		assert trieNode.search("love");
		assert trieNode.search("love");
		assert trieNode.search("natty");

		assert trieNode.search("lovelovenatty");
		assert !trieNode.search("hohoho");
	}

	static void testSuffix() {
		TrieNode trieNode;
		String key, pattern;

		trieNode = new TrieNode();
		key = "hellonattyhowareyou";
		pattern = "how";
		for (int i = 0; i < key.length(); i ++) {
			trieNode.insertSuffix(key.substring(i), i);
		}

		System.out.printf("S=%s\npattern=%s%n\n", key, pattern);
		int position = trieNode.searchSuffix(pattern);
		System.out.printf("Position of [\"%s\"] is %s", key.substring(position, position + pattern.length()), position);
	}

	private static class TrieNode {
		private TrieNode[] children = new TrieNode[26];
		private boolean isEnd;
		private List<Integer> indexes = new LinkedList<>();

		// for suffixes
		private void insertSuffix(String key, int index) {

			TrieNode curr = this;

			for (int i = 0; i < key.length(); i ++) {
				int cIndex = key.charAt(i) - 'a';
				if (curr.children[cIndex] == null) curr.children[cIndex] = new TrieNode();
				curr.children[cIndex].indexes.add(index ++);
				curr = curr.children[cIndex];
			}
		}

		private Integer searchSuffix(String s) {
			TrieNode curr = this;

			for (char c : s.toCharArray()) {
				int index = c - 'a';
				if (curr.children[index] == null) return -1;
				curr = curr.children[index];
			}

			if (curr.indexes.isEmpty()) {
				return -1;
			}
			return curr.indexes.getLast() - s.length() + 1;
		}

		// for prefixes
		private void insert(String key) {
			TrieNode curr = this;

			for (char c : key.toCharArray()) {
				int index = c - 'a';
				if (curr.children[index] == null) curr.children[index] = new TrieNode();
				curr = curr.children[index];
			}
			curr.isEnd = true;
		}

		boolean search(String key) {
			TrieNode curr = this;
			for (char c : key.toCharArray()) {
				int index = c - 'a';
				if (curr.children[index] == null) return false;
				curr = curr.children[index];
			}
			return curr.isEnd;
		}

	}
}
