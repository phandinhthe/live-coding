package org.terry.leetcode;

public class FreedomTrail {
	public static void main(String[] args) {
		new FreedomTrail().test();
	}

	public void test() {
		String ring;
		String key;
		int output;
		int actual;

		ring = "godding";
		key = "gd";
		output = 4;
		actual = findRotateSteps(ring, key);
		assert output == actual;

		ring = "godding";
		key = "godding";
		output = 13;
		actual = findRotateSteps(ring, key);
		assert output == actual;

		ring = "a";
		key = "a";
		output = 1;
		actual = findRotateSteps(ring, key);
		assert actual == output;

		ring = "abc";
		key = "aabbcc";
		output = 8;
		actual = findRotateSteps(ring, key);
		assert actual == output;

		ring = "zvyii";
		key = "iivyz";
		output = 11;
		actual = findRotateSteps(ring, key);
		assert actual == output;

	}

	/*
	Leetcode 514: Freedom trail.
	principal: DFS, Memoization
	- Start from index 'ringIndex' = '0' of 'ring', and 'keyIndex' = '0' of 'key'
	=> dfs(0, 0) , choose the ring's index ith with conditions:
		+ ring[i] == key[j], 'j'th is the index in 'key'
		+ AND Min( (i - ringIndex), len(ring) - (i-ringIndex) ). Because the rotating could go clockwise / anticlockwise.
	- After that we continue with dfs(i, j + 1).
	 */
	public int findRotateSteps(String ring, String key) {
		if (ring.length() == 1) return 1;
		return dfs(ring, key, 0, 0, new Integer[ring.length()][key.length()]);
	}

	int dfs(String ring, String key, int ringIndex, int keyIndex, Integer[][] cache) {
		if (keyIndex == key.length()) return 0; // we stop when go to the end of 'key'
		if (cache[ringIndex][keyIndex] != null) return cache[ringIndex][keyIndex]; // return value if we already cached [ringIndex, keyIndex]

		char keyChar = key.charAt(keyIndex);
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < ring.length(); i++) {
			if (ring.charAt(i) != keyChar) continue; // if char at ring's ith <> char at key's jth, ignore.
			int min = Math.abs(ringIndex - i);
			min = Math.min(min, ring.length() - min);
			result = Math.min(result, min + 1 + dfs(ring, key, i, keyIndex + 1, cache));
		}

		cache[ringIndex][keyIndex] = result;// cached after we have the min value of rotates from 'ringIndex' to 'keyIndex'.
		return result;
	}

}
