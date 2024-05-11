package org.terry.leetcode;

public class MinimumDifficultyOfAJobScheduler {
	public static void main(String... args) {
		new MinimumDifficultyOfAJobScheduler().test();
	}

	public void test() {
		Solution solution = new Solution();
		int[] jobDifficulity;
		int d;
		int output;
		int actual;

		jobDifficulity = new int[]{6, 5, 4, 3, 2, 1};
		d = 2;
		output = 7;
		actual = solution.minDifficulty(jobDifficulity, d);
		assert output == actual;

		jobDifficulity = new int[]{11, 111, 22, 222, 33, 333, 44, 444};
		d = 6;
		output = 843;
		actual = solution.minDifficulty(jobDifficulity, d);
		assert output == actual;

		jobDifficulity = new int[]{380, 302, 102, 681, 863, 676, 243, 671, 651, 612, 162, 561, 394, 856, 601, 30, 6, 257, 921, 405, 716, 126, 158, 476, 889, 699, 668, 930, 139, 164, 641, 801, 480, 756, 797, 915, 275, 709, 161, 358, 461, 938, 914, 557, 121, 964, 315};
		d = 10;
		output = 3807;
		actual = solution.minDifficulty(jobDifficulity, d);
		assert output == actual;
	}

	public class Solution {
		private static final int INF = 300_000;
		private Integer[][] cache;

		public int minDifficulty(int[] jobDifficulty, int d) {
			// edge case whey 'day' > 'jobs length'
			if (d > jobDifficulty.length) return -1;

			// initialization
			cache = new Integer[d + 1][jobDifficulty.length];

			return dfs(jobDifficulty, 0, d);
		}

		/*
		DFS:
		- job difficulty: all jobs' difficulties
		- day: keep track the day in reversed way. (Exam: if goal day is 5, 'day' here will start from 5 and decrement till 0)
		Use DFS to find deeply till we reach the limiation of 'day' or 'current'. Ore we can say it is 'remaining day'

		Note: there are 2 cases of goals:
		- 'current' equals to the jobDifficulty's length
		- 'day' is 0
		if 'current' reachs jobDifficulty's length AND 'day' reachs '0'
		=> return 0 immediately because at the last day, we just need to get the MAXIMUM of job difficulties on that day.
		=> But be careful in case the last day we have more than one values of job difficulties:
		=> if we rely on formula 'result = Max(result, current max job difficulty + dfs()),
			when the dfs() returns INF, 'result' will be itself.
			when the dfs() returns '0', 'result' here could be current max job difficulty
			=> when we have only job difficulty at the last day, we could return ZERO/INF at the day '0'. OK all.
			=> when we have more than one job difficulty at the last day, we must return the 'INF' so that the largest value
			at the last day will be updated to the 'result'.
		The 'current max' is the largest job difficulty among the jobs on the last day. This value will be updated when we
		get through all jobs in the day. We should initialize value ZERO for it because at the description of the question.
		ZERO is in the inclusive range of values of job difficulty.

		NEVER FORGET another edge case that the 'day' > 'jobs'. if it happens, let's return -1
		We just need to get throught from 'i <= current' to 'end <= length + 1 of job difficulties' length - day'. Because
		the first day's jobs can not spread till the last day. If we have 5 days and 7 jobs, the first day could have 3 jobs at most

		We should use MEMOIZATION to cache every step with [day, current index] to stay away the duplicated step when applying DFS.
		 */
		int dfs(int[] jobDifficulty, int current, int day) {
			if (current == jobDifficulty.length && day == 0) return 0;
			else if (day == 0 || current == jobDifficulty.length) return INF;

			if (cache[day][current] != null) return cache[day][current];
			int currentMax = 0;
			int result = INF;
			for (int i = current; i < jobDifficulty.length - day + 1; i++) {
				currentMax = Math.max(jobDifficulty[i], currentMax);
				result = Math.min(result, currentMax + dfs(jobDifficulty, i + 1, day - 1));
			}
			cache[day][current] = result;
			return result;
		}

	}

}