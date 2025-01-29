package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/redundant-connection/description/">
 * Redundant connection
 * </a>
 **/
public class RedundantConnection {
    public static void main(String[] args) {
        new RedundantConnection().run();
    }

    public void run() {
        int[][] edges;
        edges = new int[][]{{1, 2}, {1, 3}, {2, 3}};
        Assertions.assertArrayEquals(new int[]{2, 3}, findRedundantConnection(edges));

        edges = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        Assertions.assertArrayEquals(new int[]{1, 4}, findRedundantConnection(edges));

        edges = new int[][]{{1, 4}, {3, 4}, {1, 3}, {1, 2}, {4, 5}};
        Assertions.assertArrayEquals(new int[]{1, 3}, findRedundantConnection(edges));

        edges = new int[][]{{9, 10}, {5, 8}, {2, 6}, {1, 5}, {3, 8}, {4, 9}, {8, 10}, {4, 10}, {6, 8}, {7, 9}};
        Assertions.assertArrayEquals(new int[]{4, 10}, findRedundantConnection(edges));
    }

    // DFS O(n*n)
    //    public int[] findRedundantConnection(int[][] edges) {
//        List<List<Integer>> graph = new ArrayList<>(edges.length+1);
//        for (int i = 0; i <= edges.length; i++) {
//            graph.add(new ArrayList<>());
//        }
//
//        for (int[] edge : edges) {
//            int src = edge[0];
//            int dst = edge[1];
//            if (dfs(new boolean[edges.length + 1], graph, src, dst)) return new int[]{src, dst};
//            graph.get(src).add(dst);
//            graph.get(dst).add(src);
//        }
//        return new int[]{};
//
//    }
//
//    boolean dfs(boolean[] visited, List<List<Integer>> graph, int cur, int target) {
//        if (cur==target) return true;
//        visited[cur] = true;
//        for (Integer next : graph.get(cur)) {
//            if (visited[next]) continue;
//
//            if (dfs(visited, graph, next, target)) return true;
//        }
//
//        return false;
//    }
//
    // Union find O(n)
    public int[] findRedundantConnection(int[][] edges) {
        DSU dsu = new DSU(edges.length + 1);

        for (int[] edge : edges) {
            int src = edge[0];
            int dst = edge[1];

            if (!dsu.union(src, dst)) {
                return new int[] {src, dst};
            }
        }

        return new int[]{};
    }

    private static class DSU {
        int size = 0;
        int[] rank;
        int[] parent;

        public DSU(int size) {
            this.size = size;
            this.rank = new int[size];
            this.parent = new int[size];
            for (int i = 0; i < size; i++) {
                rank[i] = 1;
                parent[i] = i;
            }
        }

        int find(int u) {
            if (u!=parent[u]) {
                parent[u] = find(parent[u]);
            }
            return parent[u];
        }

        boolean union(int u, int v) {
            u = find(u);
            v = find(v);

            if (u==v) return false;

            if (rank[u] > rank[v]) {
                parent[v] = u;
                rank[u] += rank[v];
            } else {
                parent[u] = v;
                rank[v] += rank[u];
            }

            return true;
        }
    }
}
