package org.terry.datastructure.segmenttree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//https://codeforces.com/problemset/problem/756/C
// Solution is referenced from Mr VArtem on codeforces.
// Take a long time to research but cannot understand.
public class NikitaAndStack {

  private final String INPUT_FILE = "NikitaAndStack/NikitaAndStack.txt";

  public static void main(String[] args) {
    NikitaAndStack nikitaAndStack = new NikitaAndStack();
    nikitaAndStack.new Solution().run();
  }


  public class Solution {

    public void run() {
      FileReader fileReader = new FileReader(INPUT_FILE);
      ConsoleReader consoleReader = new ConsoleReader();
      ConsoleWriter consoleWriter = new ConsoleWriter();

      try (
          BufferedReader reader = fileReader.reader();
//          BufferedReader reader = consoleReader.reader();
          BufferedWriter writer = consoleWriter.writer()) {

        String input;
        input = reader.readLine();
        int size = Integer.parseInt(input);

        int count = size;

        int[] vals = new int[size];
        SegmentTree segmentTree = new SegmentTree(size);

        while (count >= 1) {
          input = reader.readLine();
          int[] nextInput = Arrays.stream(input.split(" ")).mapToInt(Integer::parseUnsignedInt)
              .toArray();

          int pos = nextInput[0] - 1;
          int type = nextInput[1];
          if (type == 1) {
            vals[pos] = nextInput[2];
            segmentTree.add(size - pos - 1, size - 1, 1);
          } else {
            segmentTree.add(size - pos - 1, size - 1, -1);

          }

          if (segmentTree.max[0] > 0) {
            writer.write(
                vals[size - 1 - segmentTree.getFirst()] + "\n"
            );
          } else {
            writer.write("-1\n");
          }
          count--;
        }

        writer.flush();
      } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
    }

  }

  class SegmentTree {

    int[] max, add;
    int size;

    public SegmentTree(int n) {
      max = new int[4 * n];
      add = new int[4 * n];
      size = n;
    }

    void add(int l, int r, int val) {
      add(0, size, l, r + 1, val, 0);
    }

    void add(int left, int right, int l, int r, int val, int i) {

      if (right <= l || r <= left) {
        return;
      }
      if (l <= left && right <= r) {
        max[i] += val;
        add[i] += val;
        return;
      }

      push(i);
      int mid = (left + right) >>> 1;
      add(left, mid, l, r, val, 2 * i + 1);
      add(mid, right, l, r, val, 2 * i + 2);
      max[i] = Math.max(max[2 * i + 1], max[2 * i + 2]);
    }

    void push(int i) {
      if (add[i] != 0) {
        add[2 * i + 1] += add[i];
        max[2 * i + 1] += add[i];
        add[2 * i + 2] += add[i];
        max[2 * i + 2] += add[i];
      }
      add[i] = 0;
    }

    int getFirst() {
      return get(0, size, 0);
    }

    int get(int left, int right, int i) {
      if (left + 1 == right) {
        return left;
      }
      push(i);
      int mid = (left + right) >>> 1;
      int res;
      if (max[2 * i + 1] > 0) {
        res = get(left, mid, 2 * i + 1);
      } else {
        res = get(mid, right, 2 * i + 2);
      }

      // This is unnecessary
//      max[i] = Math.max(max[2 * i + 1], max[2 * i + 2]);
      return res;
    }
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
}
