package org.terry;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * Leetcode 2699. Modify Graph Edge Weights.
 * Tuition:
 * - Initialize graph, edge weight == -1 will be ignored.
 * - Find the shortest path, using Djikstra, if:
 * - - shortest path == target => set all edge weight -1 = MAX, return edges.
 * - - shortest path != target => iterate over all edges having weight -1, set edge = 1, find the shortest path
 * - - - if result == target, set all edge weight -1 to MAX and return edges.
 * - - - else the shortest path != target, update edge += (target - shortest path), return edges.
 *
 * n = edges.length
 * COmplexity: O(nlogn)
 * Space: O(n*n)
 */
public class LiveMain {
    public static void main(String[] args) {
        new LiveMain().run();
    }

    public void run() {

    }


}