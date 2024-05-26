package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.function.BinaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://leetcode.com/problems/student-attendance-record-ii
 * Leetcode 552: Student Attendance Record II
 */
public class StudentAttendanceRecordII {
	public static void main(String[] args) {
		new StudentAttendanceRecordII().test();
	}


	public void test() {
		int n;
		int output;
		int actual;

		n = 2;
		output = 8;
		actual = checkRecord(n);
		Assertions.assertEquals(output, actual);

		n = 4;
		output = 43;
		actual = checkRecord(n);
		Assertions.assertEquals(output, actual);

		n = 10101;
		output = 183236316;
		actual = checkRecord(n);
		Assertions.assertEquals(output, actual);

	}

	/**
	 * n = 1
	 * [L]
	 * 			  0	  1	  2
	 * [A] 	0	| 1 | 1 | 0 |			// 1 Presence,	1 Late
	 * 		1  	| 1 | 0 | 0 |			// 1 Absence,
	 * <p>
	 * n = 2
	 * [L]
	 * 			  0	  1	  2
	 * [A] 	0	| 2 | 1 | 1 |
	 * 		1	| 3 | 1 | 0 |
	 */

	public int checkRecord(int n) {
		final int MOD = 1_000_000_007;
		int[][] res = new int[2][3]; // row ~ allowed number of absence, col ~ allowed number of late
		res[0][0] = 1; // 1 presence.
		res[0][1] = 1; // 1 late
		res[1][0] = 1; // 1 absence
		// ==>> `P`, `L`, `A` with n = 1;

		for (int i = 2; i <= n; i++) {
			int[][] tmp = new int[2][3];
			// pick `Presence`
			tmp[0][0] = ((res[0][0] + res[0][1]) % MOD + res[0][2]) % MOD;
			tmp[1][0] = ((res[1][0] + res[1][1]) % MOD + res[1][2]) % MOD;

			// pick `Late`
			tmp[0][1] = res[0][0];
			tmp[0][2] = res[0][1];
			tmp[1][2] = res[1][1];
			tmp[1][1] = res[1][0];

			// pick `Absence`
			tmp[1][0] = (((tmp[1][0] + res[0][0]) % MOD + res[0][1]) % MOD + res[0][2]) % MOD;
			res = tmp;
		}

//		for (int i = 1; i < n; i ++) {
//			int[][] tmp = new int[2][3];
//			for (int absence = 0; absence <= 1; absence ++) {
//				for (int late = 0; late <= 2; late ++) {
//					tmp[absence][0] += res[absence][late];
//					tmp[absence][0] %= MOD;
//
//					if (late > 0) {
//						tmp[absence][late] = res[absence][late - 1];
//					}
//					if (absence > 0) {
//						tmp[absence][0] += res[absence - 1][late];
//						tmp[absence][0] %= MOD;
//					}
//				}
//			}
//			res = tmp;
//		}
//
		// find sum from matrix.
		BinaryOperator<Integer> combineAndMod = (total, num) -> (total + num) % MOD;
		return Stream.of(res)
				.map(r -> IntStream.of(r).boxed())
				.map(r -> r.reduce(0, combineAndMod)).reduce(0, combineAndMod);
	}

}
