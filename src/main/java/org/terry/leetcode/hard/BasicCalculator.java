package org.terry.leetcode.hard;


import java.util.Deque;
import java.util.LinkedList;

// Super interesting!!!!!!
// Reversed Polish notation.
// https://leetcode.com/problems/basic-calculator/
public class BasicCalculator {

  public static void main(String[] args) {
    new BasicCalculator().run();
    char a = '1';
    char b = '2';
    System.out.println((char) (a|b));
  }

  public void run() {
    int result = new Solution().calculate("+ ((1 + 1) +(- 1 -2) - 99)");
//    int result = new Solution().calculate("-(-(-1 + 1 - 0 + 10 + 100)) + 1");
//    int result = new Solution().calculate("1-1");
    System.out.println(result);
  }

  class Solution {
    public int calculate(String s) {
      int result = 0;
      Deque<Integer> stack = new LinkedList<>();
      char[] arr = s.toCharArray();
      int current = 0;
      int sign = 1;
      for (char c : arr) {
        if (c == ' ') {
          continue;
        }
        if (c >= '0' && c <= '9') {
          current = current * 10 + (c % '0');
        } else if (c == '+' || c == '-') {
          result += (sign * current);
          current = 0;
          sign = (c == '+') ? 1 : -1;
        } else if (c == '(') {
          stack.push(result);
          stack.push(sign);
          result = 0;
          sign = 1;
        } else {
          result += (current * sign);
          result = stack.pop() * result + stack.pop();
          current = 0;
          sign = 1;
        }
      }
      result += (sign * current);
      return result;
    }
  }
}