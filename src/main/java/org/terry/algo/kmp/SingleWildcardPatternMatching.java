package org.terry.algo.kmp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class SingleWildcardPatternMatching {

  private static final String YES = "YES";
  private static final String NO = "NO";

  public static void main(String[] args) throws IOException {
    new SingleWildcardPatternMatching().run();
  }

  public void run() throws IOException {
    new Solution().run();
    new OptimizedSolution().run();
  }


  /*
  Not use any algorithm.
  Sometimes the best solution is the simplest one.
   */
  public class OptimizedSolution {

    public void run() throws IOException {
      try (
//          BufferedReader reader = new FileReader(
//              "SingleWildcardPatternMatching/SingleWildcardPatternMatching.txt").reader();
          BufferedReader reader = new ConsoleReader().reader();
          BufferedWriter writer = new ConsoleWriter().writer();
      ) {
        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        String s = reader.readLine();
        String t = reader.readLine();

        writer.write(solve(m, n, t, s));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    public String solve(int m, int n, String t, String s) {
      if (!s.contains("*")) {

        if (s.length() != t.length()) {
          return NO;
        }
        return compare(s, t, 0, n - 1) ? YES : NO;
      }

      int indexOfWildcard = s.indexOf('*');
      int s1Length = indexOfWildcard;
      int s2Length = n - 1 - indexOfWildcard;

      if (m < Math.min(s1Length, s2Length)) {
        return NO;
      }

      int start = 0;
      int end = s1Length - 1;
      boolean firstResult = compare(s, t, start, end);
      if (!firstResult) {
        return NO;
      }

      int start2 = indexOfWildcard + 1;
      int end2 = start2 + s2Length - 1;

      int start3 = m - 1 - s2Length + 1;
      int end3 = m - 1;
      if (end >= start3) {
        return NO;
      }
      boolean secondResult = compare(s, t, start2, end2, start3, end3);
      return secondResult ? YES : NO;
    }

    public boolean compare(String s1, String s2, int start1, int end1) {
      if (end1 > s1.length()-1 || end1 > s2.length() - 1) {
        return false;
      }
      for (int i = start1; i <= end1; i++) {
        if (s1.charAt(i) != s2.charAt(i)) {
          return false;
        }
      }

      return true;
    }

    public boolean compare(String s1, String s2, int start1, int end1, int start2, int end2) {
      if (end1 > s1.length()-1 | end2 > s2.length() - 1) {
        return  false;
      }
      if (end1 - start1 != end2 - start2) {
        return false;
      }

      while (start1 <= end1) {
       if (s1.charAt(start1) != s2.charAt(start2)) {
          return false;
        }
        start1++;
        start2++;
      }
      return true;
    }
  }

  /*
  Using algorithm KMP
   */
  public class Solution {

    public void run() throws IOException {
      try (
          BufferedReader reader = new FileReader(
              "SingleWildcardPatternMatching/SingleWildcardPatternMatching.txt").reader();
//          BufferedReader reader = new ConsoleReader().reader();
          BufferedWriter writer = new ConsoleWriter().writer();
      ) {
        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        String s = reader.readLine();
        String t = reader.readLine();

        writer.write(solve(m, n, t, s));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    String solve(int m, int n, String t, String s) {
      if (!s.contains("*")) {
        if (s.length() != t.length()) {
          return "NO";
        }
        return buildResult(kmp(n, m, s, t)[0]);
      }

      String[] separates = s.split("\\*");
      int firstResult = -1;
      int secondResult = -1;
      if (separates.length == 1) {
        // case '*' is at the last index;
        s = separates[0];
        n = s.length();
        firstResult = kmp(m, n, t, s)[0];
        secondResult = t.length() - 1;
      } else if (separates.length == 2 && separates[0].length() == 0) {
        // case '*' is at the first index.
        s = separates[1];
        n = s.length();
        firstResult = 0;
        secondResult = kmp(m, n, t, s)[1];
      } else if (separates.length == 0) {
        // the pattern has only one character and that character is '*'.
        return "YES";
      } else {
        // case '*' is at the mid.
        s = separates[0];
        n = s.length();
        firstResult = kmp(m, n, t, s)[0];
        if (firstResult < 0) {
          return "NO";
        }
        t = t.substring(firstResult + s.length());
        m = t.length();
        s = separates[1];
        n = s.length();
        secondResult = kmp(m, n, t, s)[1];
        if (secondResult < 0) {
          return "NO";
        }
      }

      if (firstResult < 0 || secondResult < 0) {
        return "NO";
      }

      if (firstResult == 0 && secondResult + s.length() == t.length()) {
        return "YES";
      }

      return "NO";
    }

    private String buildResult(int result) {
      return result >= 0 ? "YES" : "NO";
    }

    /*
    return the indexes if pattern exists in text:
    - 1st element is the first index.
    - 2nd element is the last index.

    if there is only one index found, it is put in 1st and 2nd array elements.
     */
    int[] kmp(int n, int m, String text, String pattern) {
      if (pattern.length() > text.length()) {
        return new int[]{-1, -1};
      }
      int[] lps = lps(pattern);

      int i = 0;
      int j = 0;
      int[] result = new int[2];
      Arrays.fill(result, -1);
      boolean isFirst = false;
      while (n - i >= m - j) {
        if (j == pattern.length()) {
          result[1] = i - pattern.length();

          if (!isFirst) {
            result[0] = result[1];
            isFirst = true;
          }
          j = lps[j - 1];
          continue;
        }
        if (text.charAt(i) == pattern.charAt(j)) {
          j++;
          i++;
          continue;
        }

        if (j > 0) {
          j = lps[j - 1];
          continue;
        }

        i++;
      }

      return result;
    }

    int[] lps(String pattern) {
      int i = 1;
      int j = 0;
      int[] result = new int[pattern.length()];
      while (i < pattern.length()) {
        if (pattern.charAt(i) == pattern.charAt(j)) {
          result[j] = j;
          j++;

          result[i] = j;
          i++;
          continue;
        }

        if (j > 0) {
          j = result[j - 1];
        }

        i++;
      }
      return result;
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
}
