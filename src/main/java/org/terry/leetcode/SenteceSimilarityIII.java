package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

/**
 * <a href="https://leetcode.com/problems/sentence-similarity-iii/submissions/1413250673/">Leetcode 1813</a>
 * <p>
 * Tuition:
 * - s1 vs s2
 * + check s1 equal s2
 * + check s1's prefix == "space".concat(s2) OR s1's suffix == s2.concat("space")
 * + s1 arr = [ [ABC] [DDDDD].................[UHUJ] [XYZ] ]
 * [Left]                  [right]
 * s2 arr = [ [ABC] [EEEE] .................[NNSD] [XYZ] ]
 * left is the start of index where s1[left] != s2[left]
 * right is the last index where s1[i] != s2[j]
 * if: s1.length - (right - left + 1) == s2.length => true else false
 */

public class SenteceSimilarityIII {

    public static void main(String[] args) {
        new SenteceSimilarityIII().run();
    }

    public void run() {
        String s1 = "My name is Haley";
        String s2 = "My Haley";
        Assertions.assertTrue(areSentencesSimilar(s1, s2));

        s1 = "of";
        s2 = "A lot of words";
        Assertions.assertFalse(areSentencesSimilar(s1, s2));

        s1 = "Eating right now";
        s2 = "Eating";
        Assertions.assertTrue(areSentencesSimilar(s1, s2));

        s1 = "c h p Ny";
        s2 = "c BDQ r h p Ny";
        Assertions.assertTrue(areSentencesSimilar(s1, s2));

        s1 = "A B C D B B";
        s2 = "A B B";
        Assertions.assertTrue(areSentencesSimilar(s1, s2));
    }

    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        // Check the special case: s1 equals s2
        if (sentence1.equals(sentence2)) return true;
        String gtSentence = (sentence1.length() > sentence2.length()) ? sentence1:sentence2;
        String ltSentence = (sentence2.length() < sentence1.length()) ? sentence2:sentence1;

        // Check the special case: s1's prefix/suffix equals s2
        if (gtSentence.startsWith(ltSentence.concat(" ")) || gtSentence.endsWith(" ".concat(ltSentence))) return true;
        String[] gtArr = gtSentence.split(" ");
        String[] ltArr = ltSentence.split(" ");

        // find the mid length of the diff between s1 and s2.
        int l = 0;
        for (; l < ltArr.length; l++) {
            if (!ltArr[l].equals(gtArr[l])) break;
        }
        int gtR = gtArr.length - 1;
        int ltR = ltArr.length - 1;
        for (; ltR >= l; ltR--) {
            if (!gtArr[gtR].equals(ltArr[ltR])) break;
            gtR--;
        }

        // the greater string length - diff's length = rs. If rs == less string length => true else false
        return gtArr.length - (gtR - l + 1)==ltArr.length;
    }
}
