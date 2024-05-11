package org.terry.datastructure.other;//package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * https://codeforces.com/problemset/problem/1538/c
 */
public class NumberOfPairs {

  public static void main(String[] args) throws IOException {

    try (
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
    ) {
      int count = Integer.parseInt(reader.readLine());
      while (count >= 1) {
        var input = reader.readLine().split(" ");
        int size = Integer.parseInt(input[0]);
        long left = Long.parseLong(input[1]);
        long right = Long.parseLong(input[2]);

        input = reader.readLine().split(" ");
        var numbers = new Long[size];
        for (int i = 0; i < size; i++) {
          numbers[i] = Long.parseLong(input[i]);
        }

        long result = solution(numbers, left, right);
        if (count == 1) {
          writer.write(result + "");
        } else {
          writer.write(result + "\n");
        }
        writer.flush();
        count--;
      }
    }

  }
  private static long solution(Long[] numbers, long left, long right) {
    Arrays.sort(numbers);
    long rs = 0L;
    for (int i = 0; i < numbers.length; i ++) {
      var ub =
          upperBound(numbers, 0, i, right - numbers[i]);
      var lb = lowerBound(
          numbers, 0, i, left  - numbers[i]);

      if (ub >= lb) {
        rs += (ub - lb);
      }
    }


    return rs;
  }

  private static int lowerBound(Long numbers[], int left, int right, long value) {
    int pos = -1;
    while (left <= right) {
      int mid = (right + left) / 2;
      if (numbers[mid] >= value) {
        right = mid - 1;
      } else {
        left = mid + 1;
        pos = mid;
      }
    }
    return pos + 1;
  }

  private static int upperBound(Long numbers[], int left, int right, long value) {
    int pos = right;
    while (left <= right) {
      int mid = left + (right-left) / 2;
      if (numbers[mid] <= value) {
        left = mid + 1;
      } else {
        pos = mid;
        right = mid - 1;
      }
    }

    return pos;
  }

  /**
   * THis version is not understandable, need to research.
   * Aware the binary search LOWER BOUND
   *   private static long solution(Long[] numbers, long left, long right) {
   *     Arrays.sort(numbers);
   *     long rs = 0L;
   *     for (int i = 0; i < numbers.length; i ++) {
   *       var ub =
   *           upperBound(numbers, 0, i, right - numbers[i]);
   *       var lb = lowerBound(
   *           numbers, 0, i, left  - numbers[i]);
   *
   *       rs += (ub - lb);
   *     }
   *     return rs;
   *   }
   *   private static int lowerBound(Long numbers[], int left, int right, long value) {
   *     int pos = right;
   *     while (left <= right) {
   *       int mid = (right + left) / 2;
   *       if (numbers[mid] >= value) {
   *         right = mid - 1;
   *         pos = mid;
   *       } else {
   *         left = mid + 1;
   *       }
   *     }
   *     return pos;
   *   }
   *
   *   private static int upperBound(Long numbers[], int left, int right, long value) {
   *     int pos = right;
   *     while (left <= right) {
   *       int mid = left + (right-left) / 2;
   *       if (numbers[mid] <= value) {
   *         left = mid + 1;
   *       } else {
   *         pos = mid;
   *         right = mid - 1;
   *       }
   *     }
   *
   *     return pos;
   *   }
   */

  /**
   * Failed because the time limit exceeded
   private static int solution(int[] numbers, int left, int right) {
   boolean checked[] = new boolean[numbers.length];
   int[] pair = new int[2];
   int result = 0;
   for (int i = 0; i < numbers.length - 1; i ++) {
   if (checked[i]) {
   continue;
   }

   pair[0] = numbers[i];
   for (int j = i+1; j < numbers.length; j ++) {
   if (checked[j]) {
   continue;
   }

   pair[1] =  numbers[j];

   int sum = Integer.sum(pair[0], pair[1]);
   if (sum <= right && sum >= left) {
   result ++;
   }
   }
   }
   return result;
   }
   **/
}