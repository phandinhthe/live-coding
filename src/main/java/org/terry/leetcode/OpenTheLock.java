package org.terry.leetcode;

import java.util.*;

/*
	Leetcode: 752. Open the lock

	Apply BFS:
	- in every step, we generate all the possible passwords and update the 'count'
	- the formla to have possible passwords from a current password is: increment and decrement in position 'index' of 4-length password.
	- in every current password, we could have 8 possible generated passwords. Be careful with the logic, '0 - 1 = 9' and '9 + 1 = 0'
	- when we have the target from the possible passwords genereated in current step => result of the question;

	Complexity: O(n), n = 10_000. (we have from '0000' to '9999' passwords to iterate).
 */

public class OpenTheLock {
	public static void main(String[] args) {
		OpenTheLock openTheLock = new OpenTheLock();
		openTheLock.new Solution().test();
	}

	private class Solution {

		public void test() {
			String[] deadends;
			String target;
			int expected;
			int actual;

			// Test function nextPossiblePasswords.
			System.out.println(
					nextPossiblePasswords("0102")
			);

			deadends = new String[]{"0201", "0101", "0102", "1212", "2002"};
			target = "0202";
			expected = 6;
			actual = openLock(deadends, target);
			assert actual == expected;

			deadends = new String[]{"8888"};
			target = "0009";
			expected = 1;
			actual = openLock(deadends, target);
			assert actual == expected;

			deadends = new String[]{"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
			target = "8888";
			expected = -1;
			actual = openLock(deadends, target);
			assert actual == expected;

			deadends = new String[]{"0000"};
			target = "8888";
			expected = -1;
			actual = openLock(deadends, target);
			assert actual == expected;

		}

		public int openLock(String[] deadends, String target) {
			final String start = "0000";
			final int MAX_SIZE = 10_000;

			// if target equals to 'start' => result will be 0.
			if (target.equals(start)) return 0;

			Deque<String> deque = new ArrayDeque<>(MAX_SIZE);
			Set<String> visited = new HashSet<>(MAX_SIZE);

			visited.addAll(Arrays.asList(deadends));
			// edge case: 'start' is in the deadends. Return '-1'.
			if (visited.contains(start)) return -1;

			// Initialize the 'count' and 'queue' to process.
			Map<String, Integer> count = new HashMap<>(MAX_SIZE);
			deque.offer(start);
			count.put(start, 0);

			while (!deque.isEmpty()) {
				String candidate = deque.poll();
				List<String> children = nextPossiblePasswords(candidate);
				for (String child : children) {
					// if we meet the target, return immediately.
					if (child.equals(target))
						return count.get(candidate) + 1;

					// if we have the possible passwords that are not visited, offer it in the queue.
					if (!visited.contains(child)) {
						// Always remember to add it to the 'visited' also.
						visited.add(child);
						deque.offer(child);
						count.compute(child, (k, v) -> count.get(candidate) + 1);
					}
				}
			}
			return -1;
		}

		// Generate all possible passwords from the current 'passowrd'
		public List<String> nextPossiblePasswords(String curPass) {
			List<String> rs = new ArrayList<>(8);
			StringBuilder sb = new StringBuilder(4).append((curPass));
			for (int i = 0; i < sb.length(); i++) {
				int record = sb.charAt(i) - '0';

				int digit = record;
				digit = (digit + 1 + 10) % 10;
				sb.setCharAt(i, (char) (digit + '0'));
				rs.add(sb.toString());

				digit = record;
				digit = (digit - 1 + 10) % 10;
				sb.setCharAt(i, (char) (digit + '0'));
				rs.add(sb.toString());

				sb.setCharAt(i, (char) (record + '0'));
			}

			return rs;
		}

	}
}
