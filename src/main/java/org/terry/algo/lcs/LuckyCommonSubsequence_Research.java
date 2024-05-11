package org.terry.algo.lcs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Codeforces 346B Solution: Mr Miguel Maurizio
 */
public class LuckyCommonSubsequence_Research {

  public static final String STRING_EMPTY = "";
  public static final String NOT_FOUND = "0";

  public static void main(String[] args) throws IOException {
    new LuckyCommonSubsequence_Research().run();
  }

  void run() throws IOException {
    try (
        BufferedReader reader = readFromFile("LuckCommonSubsequence/LuckyCommonSubsequence.txt");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
    ) {
      String s1 = reader.readLine();
      String s2 = reader.readLine();
      String virus = reader.readLine();
      String result = solve(s1, s2, virus);

      if (result != null) {
        writer.write(result);
      }


    }
  }

  private BufferedReader readFromFile(String filename) throws FileNotFoundException {
    File file = new File(this.getClass().getResource("/".concat(filename)).getFile());
    BufferedReader reader = new BufferedReader(new FileReader(file));
    return reader;
  }

  private String solve(String s1, String s2, String virus) throws IOException {

    int n1 = s1.length();
    int n2 = s2.length();
    int m = virus.length();

    int[][] failure = lpsTable(virus);
    String[][][] dp = new String[n1 + 1][n2 + 1][m];

    for (int i = 0; i <= n1; i++) {
      for (int j = 0; j <= n2; j++) {
        for (int k = 0; k < m; k++) {
          if (i > 0 && j > 0) {
            dp[i][j][k] = max(dp[i][j][k], max(dp[i][j-1][k], dp[i-1][j][k]));
            if (s1.charAt(i-1) == s2.charAt(j-1)) {
              char c = s1.charAt(i-1);

              // check if c exists in lsp table or not.
              int failureIndex = failure[k][toInt(c)];

              if (failureIndex < m) {
                // exist
                dp[i][j][failureIndex] = max(dp[i][j][failureIndex], dp[i-1][j-1][k] + "" + c);
              }
              // not exist
            }
          } else {
            dp[i][j][k] = STRING_EMPTY;
          }
        }
      }
    }
    String result = STRING_EMPTY;
    for (int k = 0; k < m; k++) {
      result = max(result, dp[n1][n2][k]);
    }

    if (result.length() == 0) {
      return NOT_FOUND;
    }
    return result;
  }

  private String max(String a, String b) {
    if (a == null) {
      return b;
    }

    if (b == null) {
      return a;
    }

    if (a.length() > b.length()) {
      return a;
    }

    return b;
  }

  //KMP
  private int[][] lpsTable(String virus) {
    int[] lps = lps(virus);

    int[][] result = new int[virus.length()][26];

    //Distribute virus index to alphabet table => create value to check and exclude virus.
    for (int len = 0; len < virus.length(); len++) {
      for (int alphabet = 0; alphabet < 26; alphabet++) {
        int prefix = len;
        char charC = (char)(alphabet + 'A');
        while (true) {
          if (virus.charAt(prefix) == charC) {
            result[len][alphabet] = prefix + 1;
            break;
          } else if (prefix != 0) {
            prefix = lps[prefix-1];
          } else {
            break;
          }
        }
      }
    }

    return result;
  }

  /**
   * Reference:
   *  - build LPS - KPM
   * https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/
   */
  private int[] lps(String pattern) {
    int len = 0;
    int i = 1;
    int lps[] = new int[pattern.length()];
    int M = pattern.length();
    lps[0] = 0;
    while (i < M) {
      if (pattern.charAt(i) == pattern.charAt(len)) {
        len++;
        lps[i] = len;
        i++;
      } else {
        if (len != 0) {
          len = lps[len - 1];
        } else {
          lps[i] = len;
          i++;
        }
      }
    }

    return lps;
  }

  private int toInt(char c) {
    return c - 'A';
  }
}