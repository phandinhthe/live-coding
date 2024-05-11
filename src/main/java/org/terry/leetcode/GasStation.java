package org.terry.leetcode;

/**
 * 134. Gas Station
 */
public class GasStation {

	public static void main(String[] args) {
		new Solution().test();
	}

	/*
	  Principal for solution:
	  - find the diff between gas[i] and cost[i]
	  => if total sum of diff is negative, we will never have a start position to complete a circuit.
	  - Find the first position that has the total on the right side is always positive.
	  => because we travel in the clocwise direction, we will go on the right side first, then back to the zero index
	  and continue on the left side of the chosen position.
	  => the right side sum is positive and the sum of total diff is postive, so we can make sure that the total on the
	  right side is greater than the left side, and the chosen position could be returned as the result.

	  total of diff: is the total sum of all the differences (gas vs cost).
	  total : is the tracking total sum of differences, it will be reset to initial value '0'. We use it to search the
	  position that has positive sum of differences on the right side.

	  ans: should be 'zero' as the initial value. Because if we set it as '-1' as the initial value, imagine the case
	  when the correct expected output is position '0'.
	 */
	public static class Solution {
		public void test() {
			int[] gas;
			int[] cost;
			int output;
			int actual;

			gas = new int[]{1, 2, 3, 4, 5};
			cost = new int[]{3, 4, 5, 1, 2};
			output = 3;
			actual = canCompleteCircuit(gas, cost);
			assert actual == output;


			gas = new int[]{2, 3, 4};
			cost = new int[]{3, 4, 3};
			output = -1;
			actual = canCompleteCircuit(gas, cost);
			assert actual == output;

			gas = new int[]{3, 3, 4};
			cost = new int[]{3, 4, 4};
			output = -1;
			actual = canCompleteCircuit(gas, cost);
			assert actual == output;

			gas = new int[]{5, 8, 2, 8};
			cost = new int[]{6, 5, 6, 6};
			output = 3;
			actual = canCompleteCircuit(gas, cost);
			assert actual == output;
		}

		public int canCompleteCircuit(int[] gas, int[] cost) {
			int size = gas.length;
			int[] diff = new int[size];
			int total = 0;
			int totalDiff = 0;
			int ans = -1;
			for (int i = 0; i < size; i++) {
				diff[i] = gas[i] - cost[i];
				totalDiff += diff[i];
				total += diff[i];
				if (total < 0) {
					total = 0;
					ans = i + 1;
				}
			}
			return totalDiff < 0 ? -1 : ans;
		}
	}
}
