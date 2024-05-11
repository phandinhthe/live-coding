package org.terry.algo.lcs;

import java.io.IOException;

/**
 * https://www.programiz.com/dsa/longest-common-subsequence
 */
public class LCSForResearch {

  public static final String WRONG_ANSWER = "WRONGANSER";

  public static void main(String[] args) throws IOException {
//    try (
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
//    ) {
//      String input[] = reader.readLine().split(" ");
//      int[] numbers = new int[input.length];
//
//      for (int i = 0; i < input.length; i ++) {
//        numbers[i] = Integer.parseInt(input[i]);
//      }

    String a = "AJKEQSLOBSROFGZ";
    String b = "OVGURWZLWVLUXTH";
    solution(a, b);
  }

  private static void solution(String a, String b) {
    int sizeA = a.length() + 1;
    int sizeB = b.length() + 1;
    int[][] lcs = new int[sizeA][sizeB];
    int max = 0;
    String[][] rs = new String[sizeA][sizeB];

    for (int i = 1; i < sizeA; i++) {
      for (int j = 1; j < sizeB; j++) {
        if (i == 0 || j == 0) {
          lcs[i][j] = 0;
          continue;
        }

        if (a.charAt(i - 1) == b.charAt(j - 1)) {
          lcs[i][j] = 1 + lcs[i - 1][j - 1];
          if (lcs[i][j] > max) {
            rs[i][j] = String.format("[%d,%d]", i, j);
          }
          continue;
        }

        lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
      }
    }

    // Following code is used to print LCS
    int index = lcs[sizeA - 1][sizeB - 1];
    int temp = index;

    // Create a character array to store the org.example.lcs.VeraAndLcs string
    char[] l = new char[index + 1];
    l[index]
        = '\0'; // Set the terminating character

    // Start from the right-most-bottom-most corner and
    // one by one store characters in org.example.lcs.VeraAndLcs[]
    int i = sizeA - 1;
    int j = sizeB - 1;
    while (i > 0 && j > 0) {
      // If current character in X[] and Y are same,
      // then current character is part of LCS
      if (a.charAt(i-1) == b.charAt(j-1)) {
        // Put current character in result
        l[index - 1] = a.charAt(i-1);

        // reduce values of i, j and index
        i--;
        j--;
        index--;
      }

      // If not same, then find the larger of two and
      // go in the direction of larger value
      else if (lcs[i - 1][j] > lcs[i][j - 1]) {
        i--;
      } else {
        j--;
      }
    }

    // Print the org.example.lcs.VeraAndLcs
    System.out.print("LCS of " + a + " and " + b
        + " is ");
    for (int k = 0; k <= temp; k++) {
      System.out.print("  " + l[k]);
    }
  }
}
