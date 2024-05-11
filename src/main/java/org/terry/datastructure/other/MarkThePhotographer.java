package org.terry.datastructure.other;//package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://codeforces.com/problemset/problem/1705/A
 */
public class MarkThePhotographer {


  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
      int count = Integer.parseInt(reader.readLine());

      while (count >= 1) {
        String input[] = reader.readLine().split(" ");
        int rowPairCount = Integer.parseInt(input[0]);
        int heightGap = Integer.parseInt(input[1]);

        input = reader.readLine().split(" ");
        int[] height = new int[input.length];
        for (int i = 0; i < input.length; i++) {
          height[i] = Integer.parseInt(input[i]);
        }

        if (count == 1) {
          writer.write(solution(rowPairCount, height, heightGap));
        } else {
          writer.write(solution(rowPairCount, height, heightGap) + "\n");
        }

        count --;
        writer.flush();
      }

    }
  }

  private static void sort(int[] number, int low, int high) {
    if (low >= high) {
      return;
    }

    int med = partition(number, low, high);
    sort(number, low, med - 1);
    sort(number, med + 1, high);
  }

  private static int partition(int number[], int low, int high) {
    int i = low, j = low;
    int med = low + (high-low)/2;
    swap(number, med, high);
    int pivot = number[high];

    while(i < high) {
      if (number[i] < pivot) {
        swap(number, i, j);
        j ++;
      }
      i++;
    }

    swap(number, j, high);
    return j;
  }

  private static void swap(int[] number, int i, int j) {
    int tmp = number[i];
    number[i] = number[j];
    number[j] = tmp;
  }

  private static String solution(int rowPairCount, int[] height, int inputGap) {
    sort(height, 0, height.length - 1);

    for (int i = 0; i < height.length - rowPairCount; i ++) {
      int gap = height[i + rowPairCount] - height[i];
      if (gap < inputGap) {
        return "NO";
      }
    }

    return "YES";
  }
}


