package org.terry.datastructure.other;

/**
 * https://leetcode.com/problems/k-inverse-pairs-array/submissions/
 */
public class KInversePairsLeetcode {

  public static void main(String[] args) {
    new KInversePairsLeetcode().run();
  }

  public void run() {
    System.out.println(new Solution().kInversePairs(3, 3));
  }


  class Solution {

    private final int MOD = 1_000_000_007;
    private final int MAX_SIZE_K = 1001;
    private final int MAX_SIZE_N = 1001;

    public int kInversePairs(int n, int k) {
//      return recursionSolution(n, k);
//      return optimizedRecursionSolution(n, k);
//      return bottomUpSolution(n, k);
      return optimizeBottomUpSolution(n,k);
    }

    /*
    The easiest way, using recursion.
     */
    public int recursionSolution(int n, int k) {
      if (n == 0) {
        return 0;
      }
      if (k == 0) {
        return 1;
      }

      int result = 0;

      for (int i = 0; i <= Math.min(n - 1, k); i++) {
        result += recursionSolution(n - 1, k - i);
        result %= MOD;
      }
      return result;
    }

    /*
    Optimized recursion solution, using memoization - cache the latest result.
     */

    /*
    Recommended level of `cache` is class-level. We can calculate in a lot of test cases and cache
    the result in every test case.
    Binary:
    - There are:
      + test case A with input N = 5, K = 7.
      + test case B with input N = 6, K = 4.
      => If we test case is processed first, we could have the result N=5, K=7 cached. Then
      in the test case B, we could reuse the result N=5, k=7 to calculate the result N=6, K=4
      quickly.
    * */
    Integer[][] cache = new Integer[MAX_SIZE_N][MAX_SIZE_K]; // represent for result of N,K.
    public int optimizedRecursionSolution(int n, int k) {
      if (n == 0) {
        return 0;
      }
      if (k == 0) {
        return 1;
      }

      // return cached result if we have
      if (cache[n][k] != null) {
        return cache[n][k];
      }

      int result = 0;
      for (int i = 0; i <= Math.min(n - 1, k); i++) {
        result += optimizedRecursionSolution(n - 1, k - i);
        result %= MOD;
      }
      // cache result after we calculated.
      cache[n][k] = result;

      return cache[n][k];
    }

    /*
    A better solution - We build bottom up.
     */

    int bottomUpSolution(int n, int k) {
      int dp[][] = new int[MAX_SIZE_N][MAX_SIZE_K];
      dp[0][0] = 1;
      // lets build from bottom up.
      for (int i = 1; i <= n; i ++) {
        for (int j = 0; j <= k; j ++) {
          // 1st edge case, k == 0 => return 1;
          if (j == 0) {
            dp[i][j] = 1;
            continue;
          }


          dp[i][j] = dp[i-1][j] + dp[i][j-1];
          dp[i][j] %= MOD;

          if (j >= i) {
            dp[i][j] = (dp[i][j] - dp[i-1][j-i] + MOD) % MOD;
          }
        }
      }

      return dp[n][k];
    }

    /*
    The optimized bottom-up solution.
    But this saves memory by using 2 1-d Array.
    */
    int optimizeBottomUpSolution(int n, int k) {
      int[] current = new int[MAX_SIZE_K];
      int[] prev = new int[MAX_SIZE_K];

      // lets build from bottom up.
      for (int i = 1; i <= n; i ++) {
        int total = 0;
        for (int j = 0; j <= k; j ++) {
          // 1st edge case, k == 0 => return 1;
          if (j == 0) {
            prev[j] = 1;
            current[j] = 1;
            total = 1;
            continue;
          }
          if (j >= i) {
            total = (total - prev[j-i] + MOD) % MOD;
          }
          total += prev[j];
          total %= MOD;
          current[j] = total;
        }

        for (int l = 0; l < MAX_SIZE_K; l ++) {
          prev[l] = current[l];
          current[l] = 0;
        }
      }

      return prev[k];
    }
  }
}
