package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <a href="https://leetcode.com/problems/check-if-grid-can-be-cut-into-sections/?envType=daily-question&envId=2025-03-25">
 * 3394. Check If grid can be cut into Sections
 * </a>
 */
public class CheckIfGridCanBeCutIntoSections {
    public static void main(String[] args) {
        new CheckIfGridCanBeCutIntoSections().run();
    }

    private void run() {
        int n;
        int[][] rectangles;

//        n = 5;
//        rectangles = new int[][]{{1, 0, 5, 2}, {0, 2, 2, 4}, {3, 2, 5, 3}, {0, 4, 4, 5}};
//        Assertions.assertEquals(true, checkValidCuts(n, rectangles));
//
//        n = 4;
//        rectangles = new int[][]{{0, 0, 1, 1}, {2, 0, 3, 4}, {0, 2, 2, 3}, {3, 0, 4, 3}};
//        Assertions.assertEquals(true, checkValidCuts(n, rectangles));
//
//        n = 4;
//        rectangles = new int[][]{{0, 2, 2, 4}, {1, 0, 3, 2}, {2, 2, 3, 4}, {3, 0, 4, 2}, {3, 2, 4, 4}};
//        Assertions.assertEquals(false, checkValidCuts(n, rectangles));

        n = 4;
        rectangles = new int[][] {{0,0,1,4},{1,0,2,3},{2,0,3,3},{3,0,4,3},{1,3,4,4}};
        Assertions.assertEquals(false, checkValidCuts(n, rectangles));
    }

    public boolean checkValidCuts(int n, int[][] rectangles) {
        return (checkLine(rectangles, 0)) || checkLine(rectangles, 1);
    }

    private static boolean checkLine(int[][] rectangles, int index) {
        int count = 1;
        Arrays.sort(rectangles, Comparator.comparingInt(a -> a[index]));
        int iStart = rectangles[0][index];
        int iEnd = rectangles[0][index + 2];

        // check horizontal line.
        for (int[] rec : rectangles) {
            int start = rec[index];
            int end = rec[index + 2];
            /**
             * | increase count when iEnd <= start
             * iStart----iEnd
             * ---------------start----end
             * | other cases
             * start----end
             * -------------iStart----iEnd
             * |
             * iStart----iEnd
             * ---------start----end
             **/
            if (iEnd <= start ) {
                iStart = iEnd;
                count++;
            }
            iEnd = Math.max(iEnd, end);
        }
        if (count >= 3) return true;
        return false;
    }
}
