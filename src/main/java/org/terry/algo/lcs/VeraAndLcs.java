package org.terry.algo.lcs;//package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//Vera And LCS - using java21
//https://codeforces.com/problemset/gymProblem/101262/B
public class VeraAndLcs {

  public static final String INPUT_FILE = "VeraAndLcs/VeraAndLcs.txt";

  public static void main(String[] args) {
    new VeraAndLcs().run();
  }

  public void run() {

    try (
        BufferedReader reader = new ConsoleReader().reader();
//        BufferedReader reader = new FileReader(INPUT_FILE).reader();
        BufferedWriter writer = new ConsoleWriter().writer();) {
      new Solution().run(
          reader,
          writer
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   * We have to find the character Result(i), 0 < i < N, the characters must be least.
   * - Result(0), LCS(0) = LCS(N-1) - Result(1), LCS(1) = LCS(N-1) - 1
   * - Result(2), LCS(2) = LCS(N-1) - 2
   * ....
   * - Result(i), LCS(i) = LCS(N-1) - i
   * - Result(i+1), LCS(i+1) = LCS(N-1) - i - 1
   * - Result(i+2), LCS(i+2) = LCS(N-1) - i - 1 - 1
   * ....
   * - Result(N-1), LCS(N-1) = LCS(N-1) - (N-1)
   */
  class Solution {

    public static final String WRONG_ANSWER = "WRONGANSWER";
    public static final char EMPTY = '\u0000';

    public void run(BufferedReader reader, BufferedWriter writer) throws IOException {
      String[] input = reader.readLine().split(" ");
      int n = Integer.parseInt(input[0]);
      int k = Integer.parseInt(input[1]);
      String array = reader.readLine();

      // Edge case:
      if (k > n) {
        writer.write(WRONG_ANSWER);
        return;
      }

      if (k == n) {
        writer.write(array);
        return;
      }

      StringBuilder rs = new StringBuilder();

      // Initialize the `lcsTable` table.
      // all the LCS of every character wil be saved here.
      // Look at this table, we could know that how many times a character could appear in the array to have the input LCS.
      int[] lcsTable = new int[26];
      for (int i = 0; i < array.length(); i++) {
        lcsTable[array.charAt(i) - 'a']++;
      }

      // solution
      String finalRs = find(array, rs, lcsTable, k);
      writer.write(finalRs);
    }

    private String find(String array, StringBuilder rs, int[] check, int lcs) {
      char theBestCandidate = EMPTY;

      int size = array.length();
      for (int i = 0; i < size; i++) {
        // choose candidate.
        char candidate;
        if (theBestCandidate != EMPTY) {
          candidate = theBestCandidate;
        } else {
          candidate = select(array.charAt(i), check, lcs);
        }

        // if the candidate is EMPTY, we found no result. Let's return wrong answer.
        if (candidate == EMPTY) {
          return WRONG_ANSWER;
        }
        // Append candidate to the result if we have find out candidate.
        rs.append(candidate);

        // if the best candidate is found.
        if (candidate != array.charAt(i)) {
          theBestCandidate = candidate;
        }

        // Reduce the appearance of the selected candidate.
        // We must check if check[candidate] is greater than ZERO or not.
        // Because if we have the best candidate and subtract it continually, we could have negative value.
        if (check[candidate - 'a'] > 0) {
          check[candidate - 'a']--;
        }

        // Reduce longestK(lcs) in every step. If lcs < N and we don't check it greater than zero, we could have negative value.
        if (lcs > 0) {
          lcs--;
        }
      }

      return rs.toString();
    }

    // Select the char to build the result string
    // We iterate over the check[] array to find the candidate char.
    /// 1st priority candidate: The candidate has count that equals lcs.
    /// 2nd priority candidate: The candidate 'c' - get from input array. We just get this candidate when lcs > 0.
    /// the last priority candidate: FINISHED - when there is no candidate count equals lcs.
    private char select(char c, int[] check, int lcs) {
      for (int i = 0; i < check.length; i++) {
        // Let's find the best candidate first
        // The best candidate has appearance == lcs
        if (check[i] == lcs) {
          return (char) ('a' + i);
        }
      }

      // If no candidate is the best, we choose `c` from input array if lcs > 0.
      // Because if lcs == 0, we must find out the best candidate or we will have no result.
      if (lcs > 0) {
        return c;
      }

      // if no best candidate and lcs == 0 already. We have no result.
      return EMPTY;
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
