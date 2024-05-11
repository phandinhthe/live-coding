package org.terry.algo.twopointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
2 pointers and could apply Binary search Lower Bound.
https://codeforces.com/contest/1721/problem/C
 */
public class MinMaxArrayTransformation {

  private static final String INPUT_FILE = "MinMaxArrayTransformation/MinMaxArrayTransformation.txt";

  public static void main(String[] args) {
    new MinMaxArrayTransformation().run();
  }

  public void run() {
    new Solution().run();
  }

  class Solution {

    void run() {

      try (
        BufferedReader reader = new FileReader(INPUT_FILE).reader();
//          BufferedReader reader = new ConsoleReader().reader();
          BufferedWriter writer = new ConsoleWriter().writer()) {
        int inputSize = Integer.parseInt(reader.readLine());
        while (inputSize >= 1) {
          int size = Integer.parseInt(reader.readLine());
          int[] a = Arrays.asList(reader.readLine().split(" ")).stream().mapToInt(Integer::parseInt)
              .toArray();
          int[] b = Arrays.asList(reader.readLine().split(" ")).stream().mapToInt(Integer::parseInt)
              .toArray();
          new Solution().solve(size, a, b, writer);
          writer.flush();
          inputSize--;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    // This solution has complexity O(nLogn).
    public int lowerBound(int a[], int value) {
      int left = 0;
      int right = a.length - 1;
      int mid;
      int result = left;
      while (left <= right) {
        mid = (right + left) / 2;
        if (a[mid] >= value) {
          result = mid;
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      }

      return result;
    }

    // optimized solution using 2pointers instead of lower bound. Complexity O(n).
    int[] findMin(int[] a, int[] b) {
      int i = 0;
      int j = 0;
      int[] min = new int[a.length];
      while (i < a.length && j < b.length) {
        if (a[i] <= b[j]) {
          min[i] = b[j] - a[i];
          i++;
          continue;
        }
        j++;
      }
      return min;
    }

    int[] findMax(int[] a, int[] b) {
      int size = a.length;
      int leftmost = size - 1;
      int[] max = new int[size];
      for (int i = size - 1; i >= 0; i--) {
        if (i < size - 1 && b[i] < a[i + 1]) {
          leftmost = i;
        }

        max[i] = b[leftmost] - a[i];
      }

      return max;
    }

    public void solve(int size, int a[], int b[], BufferedWriter writer) throws IOException {
      int[] min = findMin(a, b);
      int[] max = findMax(a, b);

      for (int k = 0; k < size; k++) {
        writer.write(min[k] + " ");
      }
      writer.write("\n");
      for (int k = 0; k < size; k++) {
        writer.write(max[k] + " ");
      }
      writer.write("\n");
    }

    class ConsoleReader {

      private BufferedReader reader() {
        return new BufferedReader(new InputStreamReader(System.in));
      }

    }

    class ConsoleWriter {

      private BufferedWriter writer() {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        return writer;
      }
    }

    class FileReader {

      private final String path;

      private FileReader(String path) {
        this.path = path;
      }

      private BufferedReader reader() throws FileNotFoundException {
        File file = new File(this.getClass().getResource("/".concat(path)).getFile());
        BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
        return reader;
      }
    }
  }

}