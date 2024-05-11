package org.terry.algo.lcs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Set;

public class LCSBasicSample {


  public static void main(String[] args) throws IOException {
    new LCSBasicSample.LCS().run();
  }


  static class LCS {

    BufferedReader fileReader(String filename) throws FileNotFoundException {
      File file = new File(this.getClass().getResource("/".concat(filename)).getFile());
      BufferedReader reader = new BufferedReader(new FileReader(file));
      return reader;
    }

    BufferedWriter consoleWriter() {
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
      return writer;
    }

    public void run() throws IOException {
      try (
          BufferedReader reader = fileReader("LCSBasicSample/LCSBasicSample.txt");
          BufferedWriter writer = consoleWriter()
      ) {
        int testCount = Integer.parseInt(reader.readLine());

        while (testCount >= 1) {

          String s1 = reader.readLine();
          String s2 = reader.readLine();
          writer.write("\n=================================================================\n");
          writer.write(String.format("The longest subsequence:\n - S1. %s\n - S2. %s\n", s1, s2));
          int[][] lcs = lcs(s1, s2);
          writer.write("Result: " + lcs[s1.length()][s2.length()]);
          writer.write("\n=================================================================\n");
          testCount--;
          writer.flush();
        }
      }
    }

    /**
     * LCS algorithm - find the longest sub-sequence.
     */
    public int[][] lcs(String s1, String s2) {
      int sizeS1 = s1.length() + 1;
      int sizeS2 = s2.length() + 1;
      int[][] lcs = new int[sizeS1][sizeS2];

      for (int i = 1; i < sizeS1; i++) {
        for (int j = 1; j < sizeS2; j++) {

          if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            lcs[i][j] = 1 + lcs[i - 1][j - 1];
          } else {
            lcs[i][j] = max(lcs[i - 1][j], lcs[i][j - 1]);
          }
        }
      }

      //print(lcs, s1, s2, sizeS1 - 1, sizeS2 - 1, new char[100], lcs[sizeS1 - 1][sizeS2 - 1], new HashSet<>());
      return lcs;
    }

    int max(int a, int b) {
      if (a >= b) {
        return a;
      }
      return b;
    }

    /*
    It is not optimized;
     */
    void print(int[][] lcs, String s1, String s2, int i, int j, char[] rs, int index,
        Set<String> set) {
      if (index == 0) {
        String s = new String(rs);
        if (set.contains(s)) {
          return;
        }
        set.add(s);
        System.out.println(s.trim());
        return;
      }

      if (i == 0 || j == 0) {
        return;
      }

      if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
        rs[index - 1] = s1.charAt(i - 1);
        print(lcs, s1, s2, i - 1, j - 1, rs, index - 1, set);
        return;
      }

      if (lcs[i][j - 1] > lcs[i - 1][j]) {
        print(lcs, s1, s2, i, j - 1, rs, index, set);
        return;
      }

      if (lcs[i - 1][j] > lcs[i][j - 1]) {
        print(lcs, s1, s2, i - 1, j, rs, index, set);
        return;
      }

      print(lcs, s1, s2, i, j - 1, rs, index, set);
      print(lcs, s1, s2, i - 1, j, rs, index, set);


    }
  }
}
