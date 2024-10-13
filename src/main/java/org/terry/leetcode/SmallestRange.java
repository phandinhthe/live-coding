package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.PriorityQueue;

/**
 * <a href="">632. Smallest Range Covering Elements from K Lists</a>
 * Tuition:
 * - Use `window` implemented by Priority Queue.
 * - The `window` must always has one element from every K list.
 * - Loop over K list, get the next element in K list[i] and push to `window`
 * - - Update max value
 * - - update the result as result = min(max value - min value)
 **/
public class SmallestRange {
    public static void main(String[] args) {
        new SmallestRange().run();
    }

    public void run() {

        List<List<Integer>> nums;
        nums = List.of(
            List.of(4, 10, 15, 24, 26), List.of(0, 9, 12, 20), List.of(5, 18, 22, 30)
        );
        int[] output = new int[]{20, 24};
        Assertions.assertArrayEquals(output, smallestRange(nums));

        nums = List.of(
            List.of(1, 2, 3), List.of(1, 2, 3), List.of(1, 2, 3)
        );
        output = new int[]{1, 1};
        Assertions.assertArrayEquals(output, smallestRange(nums));
    }


    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Num> window = new PriorityQueue<>(nums.size(), (n1, n2) -> n1.value - n2.value);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            window.offer(new Num(i, 0, nums.get(i).get(0)));
            max = Math.max(max, nums.get(i).get(0));
        }

        int[] res = new int[]{window.peek().value, max};
        while (true) {
            Num min = window.peek();
            int index = min.index;
            int listIndex = min.listIndex;
            if (nums.get(listIndex).size()==index + 1) break;
            index++;
            max = Math.max(max, nums.get(listIndex).get(index));
            window.poll();
            window.offer(
                new Num(listIndex, index, nums.get(listIndex).get(index))
            );

            res = min(res, window.peek().value, max);
        }

        return res;
    }

    int[] min(int[] arr, int min, int max) {
        int interval = arr[1] - arr[0];
        if (max - min < interval) {
            return new int[]{min, max};
        } else if (max - min > interval) {
            return arr;
        }

        if (min < arr[0]) return new int[]{min, max};
        return arr;
    }

    record Num(int listIndex, int index, int value) {
    }
}
