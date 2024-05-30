package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

/**
 *	https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/description/
 *	1404. Number of Steps to Reduce a Number in Binary Representation to One
 *	- loop over s.charArray from `length-1` to `1`
 * 	- Keep track `extra` and current digit `n`
 *
 * 	- complexity: O(n)
 */
public class NumberOfStepToReduceANumberInBinaryRepresentationToOne {
	public static void main(String[] args) {
		new NumberOfStepToReduceANumberInBinaryRepresentationToOne().test();
	}

	public void test() {
		String s;
		int output;
		int actual;

		s = "1101";
		output = 6;
		actual = numSteps(s);
		Assertions.assertEquals(output, actual);

		s = "10";
		output = 1;
		actual = numSteps(s);
		Assertions.assertEquals(output, actual);

		s = "1";
		output = 0;
		actual = numSteps(s);
		Assertions.assertEquals(output, actual);

	}

	public int numSteps(String s) {
		char[] arr = s.toCharArray();
		int i = arr.length - 1;
		int res = 0;
		int extra = 0;
		while (i >= 0) {
			int n = arr[i] - '0';
			n += extra;
			if (n == 1) {
				n = 1;
				extra = 0;
			} else if (n > 1) {
				n = 0;
			}

			if (i == 0 && n == 0) {
				return res + 1;
			}
			if (i == 0 && n == 1) return res;

			if (n == 0) res++;
			else {
				res += 2;
				extra = 1;
			}

			i--;
		}
		return res;
	}
}
