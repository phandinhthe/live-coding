package org.terry.datastructure.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://codeforces.com/problemset/problem/1726/A
 */
public class MainakAndArray {

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

      int testCount = Integer.parseInt(reader.readLine());

      while (testCount >= 1) {
        int size = Integer.parseInt(reader.readLine());
        int numbers[] = new int[size];
        String[] inputs = reader.readLine().split(" ");
        for (int i = 0; i < size; i++) {
          numbers[i] = Integer.parseInt(inputs[i]);
        }

        if (testCount > 1) {
          writer.append(solve(numbers, size) + "\n");

        } else {
          writer.append("" + solve(numbers, size));
        }

//        writer.write("\n");
//        for (int i = 0; i < size; i ++) {
//          writer.write(numbers[i] + " ");
//        }
        writer.flush();
        testCount--;
      }
    }
  }

  private static int solve(int[] numbers, int size) {

    if (size == 1) {
      return 0;
    }
    int max = 0;
    int tmpMax = 0;
    // for the first case: the element from index 0 could go to the rest elements
    for (int i = 1; i < size; i++) {
      tmpMax = numbers[i] - numbers[0];
      max = Math.max(tmpMax, max);

      if (max == 967) {
        System.out.println("First case:" + i);
        return -1;
      }
    }

    // for the second case: all the non-zero-index elements could go to its previous element.
    for (int i = 1; i < size; i++) {
      tmpMax = numbers[i-1] - numbers[i];
      max = Math.max(tmpMax, max);
    }

    // for the third case: all the non-zero-index elements could go to the last element.
    for (int i = 1; i < size - 1; i++) {
      tmpMax = numbers[size - 1] - numbers[i];
      max = Math.max(tmpMax, max);
      if (max == 967) {
        System.out.println("Third case:" +i);
        return -1;
      }
    }

    return max;
  }

}
