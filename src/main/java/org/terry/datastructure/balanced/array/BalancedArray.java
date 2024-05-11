package org.terry.datastructure.balanced.array;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Remember to remove package when submitting to codeforces.
 */
public class BalancedArray {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    PrintWriter out = new PrintWriter(System.out);
    int testCount = Integer.parseInt(scanner.nextLine());

    while (testCount >= 1) {
      int test = Integer.parseInt(scanner.nextLine());
      if (test % 4 != 0) {
        out.println("NO");
      } else {
        out.println("YES");
        answer(test, out);
      }
      testCount--;
    }
    out.flush();
    System.exit(0);
  }

  private static void answer(int test, PrintWriter out) {
    int even = 2;
    int balance = 0;
    for (int i = 0; i < test / 2 - 1; i++) {
      out.printf("%d ", even);
      even += 2;
      balance += 1;
    }
    out.printf("%d ", even);
    out.flush();
    balance += 1;

    int odd = 1;
    for (int i = 0; i < test / 2 - 1; i++) {

      out.printf("%d ", odd);
      odd += 2;
    }
    out.printf("%d\n", odd + balance);
    out.flush();
  }
}