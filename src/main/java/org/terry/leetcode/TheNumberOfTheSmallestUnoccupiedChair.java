package org.terry.leetcode;


import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <a href="https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair">1942. The number of the smallest unoccupied chair</a>
 * tuition:
 * - sort the times by `arrival`
 * - allocate 2 heaps: `used` and `available`
 * - iterate all the `time` in the sorted list
 * => while cur.arrival >= heap.peek.leaving: heap.pop and push free `chair` to `available`
 * => Then, get the `available`.pop and assign to `cur.chair`
 * => Then, if `cur`.arrival = target.arrival : return result is the cur.chair.
 */

public class TheNumberOfTheSmallestUnoccupiedChair {
    public static void main(String[] args) {
        new TheNumberOfTheSmallestUnoccupiedChair().run();
    }

    public void run() {
        int[][] times = new int[][]{{1, 4}, {2, 3}, {4, 6}};
        int targetFriend = 1;
        int expected = 1;
        int actual = smallestChair(times, targetFriend);
        Assertions.assertEquals(expected, actual);

        times = new int[][]{{33889, 98676}, {80071, 89737}, {44118, 52565}, {52992, 84310}, {78492, 88209}, {21695, 67063}, {84622, 95452}, {98048, 98856}, {98411, 99433}, {55333, 56548}, {65375, 88566}, {55011, 62821}, {48548, 48656}, {87396, 94825}, {55273, 81868}, {75629, 91467}};
        targetFriend = 6;

        expected = 2;
        actual = smallestChair(times, targetFriend);
        Assertions.assertEquals(expected, actual);
    }

    public int smallestChair(int[][] times, int targetFriend) {
        List<Time> list = new ArrayList<>(times.length);
        int index = 0;
        for (int[] time : times) {
            list.add(new Time(-1, time[0], time[1]));
        }
        Collections.sort(list, (t1, t2) -> {
            if (t1.arrival!=t2.arrival) return t1.arrival - t2.arrival;
            else return t1.arrival - t2.arrival;
        });
        PriorityQueue<Time> used = new PriorityQueue<>((t1, t2) -> t1.leaving - t2.leaving);
        PriorityQueue<Integer> available = new PriorityQueue<>(IntStream.range(0, times.length).boxed().collect(Collectors.toList()));

        for (Time cur : list) {
            while (!used.isEmpty() && cur.arrival >= used.peek().leaving) {
                available.offer(
                    used.poll().chair
                );
            }
            cur.chair = available.poll();
            used.offer(cur);

            if (cur.arrival==times[targetFriend][0]) return cur.chair;
        }
        return -1;
    }

    static class Time {
        int chair = -1, arrival = -1, leaving = -1;

        Time(int chair, int arrival, int leaving) {
            this.chair = chair;
            this.arrival = arrival;
            this.leaving = leaving;
        }
    }

}
