package org.terry.leetcode.hard;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/description/">
 * 2493. Divide Nodes Into the Maximum Number of Groups
 * </a>
 *
 * Note:
 * In graph theory, a bipartite graph (or bigraph) is a graph whose vertices (or nodes) can be divided into two disjoint sets X and Y such that every edge connects a vertex in X to one in Y.
 * with Bipartite graph, return -1.
 */
public class DivideNodesIntoTheMaximumNumbersOfGroups {
    public static void main(String[] args) {
        new DivideNodesIntoTheMaximumNumbersOfGroups().run();
    }

    private void run() {
        int n;
        int[][] edges;

//        n = 6;
//        edges = new int[][]{{1, 2}, {1, 4}, {1, 5}, {2, 6}, {2, 3}, {4, 6}};
//        Assertions.assertEquals(
//            4,
//            magnificentSets(n, edges)
//        );
//        n = 3;
//        edges = new int[][]{{1, 2}, {2, 3}, {3, 1}};
//        Assertions.assertEquals(
//            -1,
//            magnificentSets(n, edges)
//        );
//
//        n = 2;
//        edges = new int[][]{{1, 2}};
//        Assertions.assertEquals(
//            2,
//            magnificentSets(n, edges)
//        );

        n = 15;
        edges = new int[][]{{9, 15}, {2, 3}, {13, 11}, {6, 15}, {14, 9}, {10, 13}, {14, 2}, {15, 5}, {1, 2}, {13, 15}, {1, 12}, {14, 13}, {9, 6}, {4, 1}, {15, 2}, {7, 15}, {10, 3}, {10, 9}, {11, 8}, {4, 14}};
        Assertions.assertEquals(
            -1,
            magnificentSets(n, edges)
        );
    }

    public int magnificentSets(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        Set<Integer> visit = new HashSet<>();
        int res = 0;
        for (int src = 1; src <= n; src++) {
            if (visit.contains(src)) continue;
            Pair pair = maxGroupCnts(graph, src, visit);
            if (pair == Pair.INVALID) {return -1;}
            Set<Integer> adj = pair.verticles();
            int maxCnt = pair.groupCount();
            for (int nei : adj) {
                Pair next = maxGroupCnts(graph, nei, visit);
                if (next==Pair.INVALID) {
                    return -1;
                }
                maxCnt = Math.max(maxCnt, next.groupCount());
            }
            res += maxCnt;
        }
        return res;
    }

    Pair maxGroupCnts(List<List<Integer>> graph, int src, Set<Integer> visit) {
        Queue<Verticle> queue = new LinkedList<>();
        queue.offer(new Verticle(1, src));
        Map<Integer, Integer> dist = new HashMap<>();
        dist.put(src, 1);
        int maxGroup = 1;
        while (!queue.isEmpty()) {
            Verticle curVerticle = queue.poll();
            int curGroup = curVerticle.group();
            int curIndex = curVerticle.index();

            for (int nei : graph.get(curIndex)) {
                if (dist.containsKey(nei)) {
                    if (dist.get(nei)==curGroup) {
                        return Pair.INVALID;
                    }
                    continue;
                }
                queue.offer(new Verticle(curGroup + 1, nei));
                dist.put(nei, curGroup + 1);
                maxGroup = Math.max(maxGroup, curGroup + 1);
                visit.add(nei);
            }
        }
        return new Pair(maxGroup, dist.keySet());
    }

    record Verticle(int group, int index) {
    }

    record Pair(int groupCount, Set<Integer> verticles) {
        public static final Pair INVALID = new Pair(-1, Collections.emptySet());
    }
}
