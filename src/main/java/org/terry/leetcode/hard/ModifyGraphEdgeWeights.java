package org.terry.leetcode.hard;

import org.junit.jupiter.api.Assertions;

import java.util.*;

public class ModifyGraphEdgeWeights {
    public static void main(String[] args) {
        new ModifyGraphEdgeWeights().run();
    }

    public void run() {
        int n;
        int[][] edges;
        int source;
        int destination;
        int target;
        int[][] output;
        // input:

        n = 5;
        edges = new int[][]{{4, 1, -1}, {2, 0, -1}, {0, 3, -1}, {4, 3, -1}};
        source = 0;
        destination = 1;
        target = 5;
        output = new int[][]{{4, 1, 1}, {2, 0, 1}, {0, 3, 3}, {4, 3, 1}};
        assertEquals(output, modifiedGraphEdges(n, edges, source, destination, target));

        n = 5;
        edges = new int[][]{{4, 1, -1}, {2, 0, -1}, {0, 3, -1}, {4, 3, -1}};
        source = 0;
        destination = 1;
        target = 5;
        output = new int[][]{{4, 1, 1}, {2, 0, 1}, {0, 3, 3}, {4, 3, 1}};
        assertEquals(output, modifiedGraphEdges(n, edges, source, destination, target));

        n = 3;
        edges = new int[][]{{0, 1, -1}, {0, 2, 5}};
        source = 0;
        destination = 2;
        target = 6;
        output = new int[][]{};
        assertEquals(output, modifiedGraphEdges(n, edges, source, destination, target));
    }

    void assertEquals(int[][] expected, int[][] actual) {
        Assertions.assertEquals(expected.length, actual.length);

        int expectedTotal = 0, actualTotal = 0;
        for (int i = 0; i < expected.length; i++) {
            expectedTotal += expected[i][2];
            actualTotal += actual[i][2];
        }
        Assertions.assertEquals(expectedTotal, actualTotal);
    }

    int djikstra(int n, Map<Integer, Map<Integer, Integer>> graph, int src, int des) {
        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparing(k -> k[1]));
        boolean[] checked = new boolean[n];
        minHeap.offer(new int[]{src, 0});
        while (!minHeap.isEmpty()) {
            int node = minHeap.peek()[0];
            int weight = minHeap.poll()[1];
            if (node==des) return weight;
            if (checked[node]) continue;
            checked[node] = true;

            for (Map.Entry<Integer, Integer> entry : graph.getOrDefault(node, Collections.emptyMap()).entrySet()) {
                int nei = entry.getKey();
                if (checked[nei]) continue;
                int neiWeight = entry.getValue();
                minHeap.offer(new int[]{nei, weight + neiWeight});
            }
        }
        return 2_000_000_001;
    }

    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        int MAX = 1_000_000_001;
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            if (w==-1) continue;
            graph.computeIfAbsent(u, k -> new HashMap<>()).put(v, w);
            graph.computeIfAbsent(v, k -> new HashMap<>()).put(u, w);
        }

        int curDist = djikstra(n, graph, source, destination);
        if (curDist < target) return new int[][]{};

        for (int[] edge : edges) {
            if (edge[2]!=-1) continue;
            int delta = target - curDist;
            if (delta==0) {
                edge[2] = MAX;
                continue;
            }
            edge[2] = 1;
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, key -> new HashMap<>()).put(v, 1);
            graph.computeIfAbsent(v, key -> new HashMap<>()).put(u, 1);
            curDist = djikstra(n, graph, source, destination);
            if (curDist < target) {
                edge[2] += (-curDist + target);
                curDist = target;
            }
        }

        return curDist==target ? edges:new int[][]{};
    }

}
