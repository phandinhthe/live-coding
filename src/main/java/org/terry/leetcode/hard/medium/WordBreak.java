package org.terry.leetcode.hard.medium;

import java.util.List;

public class WordBreak {

  public static void main(String[] args) {
    List<String> list = List.of("leet", "code");
    var result = new WordBreak().new Solution()
        .wordBreak("leetcode", list);

    System.out.println(result);
  }

  class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {
      boolean[] visited = new boolean[s.length()];
      boolean[] dp = new boolean[s.length()+1];

      dp[0] = true;

      for (int i = 0; i < s.length(); i ++) {
          for (String word : wordDict) {
              if (!dp[i]) continue;
              boolean isEqual = compare(i, s, word);
              int lastIndex = i - 1 + word.length();
              if (!isEqual) continue;
              dp[lastIndex + 1] = true;
              if (lastIndex == s.length() - 1) return true;
          }
      }

      return false;

    }

    boolean compare(int start, String s, String compareString) {
        if (start + compareString.length() > s.length()) return false;
        for (int i = 0; i < compareString.length(); i ++) {
            if (s.charAt(start) != compareString.charAt(i)) return false;
            start ++;
        }
        return true;
    }
  }
}
