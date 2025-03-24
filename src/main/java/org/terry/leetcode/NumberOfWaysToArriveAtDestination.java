package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.*;
import java.util.stream.IntStream;

/**
 * <a href="https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/?envType=daily-question&envId=2025-03-23"></a>
 * 1976. Number of Ways to Arrive at Destination
 */
public class NumberOfWaysToArriveAtDestination {
    public static void main(String[] args) {
        new NumberOfWaysToArriveAtDestination().run();
    }

    public void run() {
        // input
        int n;
        int[][] roads;

        n = 7;
        roads = new int[][]{{0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3}, {6, 3, 3}, {3, 5, 1}, {6, 5, 1}, {2, 5, 1}, {0, 4, 5}, {4, 6, 2}};
        Assertions.assertEquals(4, countPaths(n, roads));

        n = 2;
        roads = new int[][]{{1, 0, 10}};
        Assertions.assertEquals(1, countPaths(n, roads));

        n = 1;
        roads = new int[][]{};
        Assertions.assertEquals(1, countPaths(n, roads));

        n = 5;
        roads = new int[][]{{0, 1, 1}, {1, 2, 4}, {0, 4, 3}, {3, 2, 5}, {3, 4, 1}, {3, 0, 5}, {1, 3, 1}};
        Assertions.assertEquals(2, countPaths(n, roads));

        n = 6;
        roads = new int[][]{{0, 1, 1000000000}, {0, 3, 1000000000}, {1, 3, 1000000000}, {1, 2, 1000000000}, {1, 5, 1000000000}, {3, 4, 1000000000}, {4, 5, 1000000000}, {2, 5, 1000000000}};
        Assertions.assertEquals(1, countPaths(n, roads));
    }

    public int countPaths(int n, int[][] roads) {
        PriorityQueue<Vertice> pq = new PriorityQueue<>();
        long[] minCost = new long[n];
        long[] pathCnt = new long[n];

        pq.offer(new Vertice(0, 0));
        pathCnt[0] = 1L;

        Arrays.fill(minCost, Long.MAX_VALUE);
        final int MOD = 1_000_000_000 + 7;
        long[][] graph = new long[n][n];
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int w = road[2];

            graph[u][v] = w;
            graph[v][u] = w;
        }

        while (!pq.isEmpty()) {
            Vertice curVertice = pq.poll();
            int cur = curVertice.pos;
            long cost = curVertice.cost;

            for (int nei = 0; nei < n; nei ++) {
                if (graph[cur][nei] == 0L) continue;
                if (cost + graph[cur][nei] < minCost[nei]) {
                   minCost[nei] = cost + graph[cur][nei];
                   pathCnt[nei] = pathCnt[cur];
                   pq.offer(new Vertice(nei, minCost[nei]));

                } else if (cost + graph[cur][nei] == minCost[nei]) {
                    pathCnt[nei] = (pathCnt[nei] + pathCnt[cur]) % MOD;
                }
            }
        }

        return (int) pathCnt[n-1];
    }

    public static class Vertice implements Comparable<Vertice> {
        private int pos;
        private long cost;

        public Vertice(int pos, long cost) {
            this.pos = pos;
            this.cost = cost;
        }

        @Override
        public int compareTo(Vertice o) {
            return (int) (this.cost - o.cost);
        }
    }

}