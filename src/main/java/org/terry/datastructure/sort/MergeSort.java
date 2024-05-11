package org.terry.datastructure.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Random;


public class MergeSort {

  public static final String INPUT_FILE = "Mergesort/Mergesort.in";
  public static final String OUTPUT_FILE = "Mergesort/Mergesort.out";

  public static void main(String[] args) {
    int size = 50;
    int batch = 1000;

    MergeSort mergeSort = new MergeSort();
    Solution solution = mergeSort.new Solution();
    solution.generate(size, 0, size);

    try (BufferedReader reader = mergeSort.new FileReader(INPUT_FILE).reader();
        BufferedWriter writer = mergeSort.new FileWriter(OUTPUT_FILE).writer()) {
      int[] numbers = Arrays.stream(reader.readLine().split(" "))
          .mapToInt(Integer::parseUnsignedInt).toArray();

      solution.sort(numbers, size);

      writer.write(String.valueOf(numbers[0]));
      for (int i = 1; i < size; i++) {
        writer.write(" ".concat(String.valueOf(numbers[i])));
        if (i % batch == 0) {
          writer.flush();
        }
      }
      writer.flush();

      solution.test(numbers);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  class Solution {

    void sort(int[] numbers, int size) {
      mergeSort(numbers, 0, size - 1);
    }

    private void mergeSort(int[] numbers, int left, int right) {
      if (left >= right) {
        return;
      }
      int mid = (left + right) / 2;

      mergeSort(numbers, left, mid);
      mergeSort(numbers, mid + 1, right);
      merge(numbers, left, mid, right);
    }

    private void merge(int[] numbers, int left, int mid, int right) {

      int[] arr0 = copy(numbers, left, mid);
      int[] arr1 = copy(numbers, mid + 1, right);
      int i = 0, j = 0;
      int k = left;
      while (i < arr0.length && j < arr1.length) {
        if (arr0[i] < arr1[j]) {
          numbers[k++] = arr0[i ++];
        } else {
          numbers[k++] = arr1[j ++];
        }
      }

      while (i < arr0.length) {
        numbers[k++] = arr0[i++];
      }

      while (j < arr1.length) {
        numbers[k++] = arr1[j++];
      }
    }

    int[] copy(int[] numbers, int start, int end) {
      int k = 0;
      int[] ans = new int[end - start + 1];
      for (int i = start; i <= end; i++) {
        ans[k++] = numbers[i];
      }

      return ans;
    }

    void test(int[] numbers) {
      for (int i = 0; i < numbers.length - 1; i++) {
        if (numbers[i] > numbers[i + 1]) {
          System.out.printf("Wrong at in index %d and %d \n", i, i + 1);
          System.out.printf("%d > %d", numbers[i], numbers[i + 1]);
          return;
        }
      }

      System.out.printf("Passed\n");
    }

    void generate(int size, int smallest, int largest) {

      try (BufferedWriter writer = new FileWriter(INPUT_FILE).writer();) {
        Random random = new Random();
        int randNumber = random.nextInt(largest - smallest + 1);
        writer.write("" + randNumber);
        for (int i = 1; i < size; i++) {
          randNumber = random.nextInt(largest - smallest + 1);
          writer.write((" ") + randNumber);
          if (i % 1_000 == 0) {
            writer.flush();
          }
        }
        writer.flush();
      } catch (Exception ex) {
        ex.printStackTrace();
      }

    }
  }


  private class ConsoleReader {

    private BufferedReader reader() {
      return new BufferedReader(new InputStreamReader(System.in));
    }

  }

  private class ConsoleWriter {

    private BufferedWriter writer() {
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
      return writer;
    }
  }

  private class FileReader {

    private String path;

    private FileReader(String path) {
      this.path = path;
    }

    private BufferedReader reader() throws FileNotFoundException {
      File file = new File(this.getClass().getResource("/".concat(path)).getFile());
      BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
      return reader;
    }
  }

  private class FileWriter {

    String path;

    FileWriter(String path) {
      this.path = path;
    }

    private BufferedWriter writer() throws IOException {
      String path = this.getClass().getResource("/").getPath().concat(this.path);
      new File(path).createNewFile();
      File file = new File(path);
      BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(file));
      return writer;
    }
  }
}
