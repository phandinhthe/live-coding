package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * Leetcode 1334: Find the city with the smallest number of neighbors at a threshold distance.
 * <p></p>
 * tuition:
 * - build graph[][].
 * - use floy-warshall to find the shortest of a vertex `i` to the other n-1  vertex
 * - another nested loop over `graph` to count all graph[i][j] <= threshold
 */

public class FindTheCityWIthTheSmallestNumberOfNeighborsAtAThresholdDistance {
    public static void main(String[] args) {
        new FindTheCityWIthTheSmallestNumberOfNeighborsAtAThresholdDistance().test();
    }


    public void test() {
        int n;
        int[][] edges;
        int distanceThreshold;
        int output, expected;

        n = 4;
        edges = new int[][]{{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
        distanceThreshold = 4;
        output = findTheCity(n, edges, distanceThreshold);
        expected = 3;
        Assertions.assertEquals(expected, output);

        n = 5;
        edges = new int[][]{{0, 1, 2}, {0, 4, 8}, {1, 2, 3}, {1, 4, 2}, {2, 3, 1}, {3, 4, 1}};
        distanceThreshold = 2;
        output = findTheCity(n, edges, distanceThreshold);
        expected = 0;
        Assertions.assertEquals(expected, output);

        n = 6;
        edges = new int[][]{{0, 1, 10}, {0, 2, 1}, {2, 3, 1}, {1, 3, 1}, {1, 4, 1}, {4, 5, 10}};
        distanceThreshold = 20;
        output = findTheCity(n, edges, distanceThreshold);
        expected = 5;
        Assertions.assertEquals(expected, output);

    }

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        final int MAX = 10_001;

        // build graph.
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], MAX);
        }
        for (int[] edge : edges) {
            int w = edge[2];
            int u = edge[0];
            int v = edge[1];
            if (w > distanceThreshold) continue;
            graph[u][v] = graph[v][u] = w;
        }

        // floy-warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (i == k) continue; // ignore i == k
                for (int j = 0; j < n; j++) {
                    if (j == k || i == j) continue; // ignore j ==k && i == j
                    int w = graph[i][k] + graph[k][j];
                    graph[i][j] = Math.min(graph[i][j], w);
                }
            }
        }

        // Loop over graph[] again to count and find the result.
        int min = Integer.MAX_VALUE;
        int res = -1;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (graph[i][j] != 0 && graph[i][j] <= distanceThreshold) {
                    count++;
                }
            }
            if (count < min) {
                min = count;
                res = i;
            } else if (count == min) {
                res = Math.max(res, i);
            }
        }
        return res;
    }


}
