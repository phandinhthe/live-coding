package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/count-the-number-of-complete-components/description/?envType=daily-question&envId=2025-03-22">
 *     2685. Count the number of complete components
 * </a>
 */
public class CountTheNumberOfCompleteComponents {
    public static void main(String[] args) {
        new CountTheNumberOfCompleteComponents().run();
    }

    void run() {
        int n;
        int[][] edges;

        n = 6;
        edges = new int[][]{{0, 1}, {0, 2}, {1, 2}, {3, 4}};
        Assertions.assertEquals(3, countCompleteComponents(n, edges));

        n = 6;
        edges = new int[][]{{0, 1}, {0, 2}, {1, 2}, {3, 4}, {3, 5}};
        Assertions.assertEquals(1, countCompleteComponents(n, edges));

        n = 5;
        edges = new int[][]{{1, 2}, {3, 4}, {1, 4}, {2, 3}, {1, 3}, {2, 4}};
        Assertions.assertEquals(2, countCompleteComponents(n, edges));

    }

    public int countCompleteComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int res = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (set.contains(i)) continue;
            if (bfs(graph, set, i)) res++;
        }
        return res;
    }

    boolean bfs(Map<Integer, List<Integer>> graph, Set<Integer> set, int cur) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(cur);
        int nodeCnt = 0;
        int edgeCnt = 0;
        while (!queue.isEmpty()) {
            int next = queue.poll();
            if (set.contains(next)) continue;
            set.add(next);

            List<Integer> neighbors = graph.getOrDefault(next, Collections.emptyList()).stream()
                .filter(n -> !set.contains(n)).toList();
            edgeCnt += neighbors.size();
            nodeCnt++;
            for (int nei : neighbors) {
                if (set.contains(nei)) continue;
                queue.offer(nei);
            }
        }

        // formula :
        // Node count: n
        // connected sub graph will have: n*(n-1)/2
        return nodeCnt * (nodeCnt - 1)/2==edgeCnt;
    }

}
