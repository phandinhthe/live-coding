package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/hand-of-straights
 * Hand Of Straights
 * Note:
 * - Sort in ascending order. O(nlogn)
 * - Use map to record count of every number in `hand`.
 * - loop over all `i` in `hand`:
 * 1. j = i + 1, check `j` in map count, if not return false.
 * 2. decrease count of `j` in map count.
 * 3. finally return true.
 *
 * O(nlogn*m)
 **/

public class HandOfStraights {
	public static void main(String[] args) {
		new HandOfStraights().test();
	}

	public void test() {

		int[] hand;
		int groupSize;
		boolean output;
		boolean actual;

		hand = new int[]{1, 2, 3, 6, 2, 3, 4, 7, 8};
		groupSize = 3;
		output = true;
		actual = isNStraightHand(hand, groupSize);
		Assertions.assertEquals(output, actual);

		hand = new int[]{1, 2, 3, 4, 5};
		groupSize = 4;
		output = false;
		actual = isNStraightHand(hand, groupSize);
		Assertions.assertEquals(output, actual);

		hand = new int[]{1};
		groupSize = 1;
		output = true;
		actual = isNStraightHand(hand, groupSize);
		Assertions.assertEquals(output, actual);

		hand = new int[]{1, 2, 3, 6, 2, 3, 4, 4, 7, 8};
		groupSize = 3;
		output = false;
		actual = isNStraightHand(hand, groupSize);
		Assertions.assertEquals(output, actual);

	}


	public boolean isNStraightHand(int[] hand, int groupSize) {
		Arrays.sort(hand);
		Map<Integer, Integer> count = new HashMap<>();
		for (int i : hand) {
			count.compute(i, (k, v) -> null == v ? 1 : ++v);
		}
		for (int i : hand) {
			if (count.get(i) == 0) continue;
			count.compute(i, (k, v) -> --v);
			int j = i + 1;
			while (j - i < groupSize) {
				if (count.getOrDefault(j, 0) == 0) return false;
				count.compute(j, (k, v) -> --v);
				j++;
			}
		}
		return true;
	}
}
