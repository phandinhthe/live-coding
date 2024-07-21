package org.terry.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Leetcode 2392: Build a matrix with conditions
 * <p>
 * Tuition:
 * - Using topology sort,
 * - - find the rows in order
 * - - find the cols in order
 * - Use Visited[0...k], 0: not visited, 1 : visiting, 2 : visited
 * - if the `cur` is in visiting, => cyclic
 * - result[i][j] check rows[i] == cols[j] (with i,j < k)
 */

public class BuildAMatrixWithConditions {
    public static void main(String[] args) {

    }

    public void test() {
        int k;
        int[][] rowConditions;
        int[][] colConditions;
        int[][] actual;
        int[][] output;

        k = 3;
        rowConditions = new int[][]{{1, 2}, {3, 2}};
        colConditions = new int[][]{{2, 1}, {3, 2}};
        actual = new int[][]{{3, 0, 0}, {0, 0, 1}, {0, 2, 0}};
        output = buildMatrix(k, rowConditions, colConditions);
        print(actual);
        print(output);

        //k = 3,
        k = 3;
        rowConditions = new int[][]{{1, 2}, {2, 3}, {3, 1}, {2, 3}};
        colConditions = new int[][]{{2, 1}};
        actual = new int[][]{};
        output = buildMatrix(k, rowConditions, colConditions);
        print(actual);
        print(output);
    }

    void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t\t");
            }
            System.out.println();
        }
        System.out.println("======== ======== =========");
    }

    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        List<Integer> rowOrder = topoSort(rowConditions, k);
        List<Integer> columnOrder = topoSort(colConditions, k);

        if (rowOrder.isEmpty() || columnOrder.isEmpty()) {
            return new int[][]{};
        }
        int[][] result = new int[k][k];

        // append results
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (rowOrder.get(i).equals(columnOrder.get(j))) {
                    result[i][j] = rowOrder.get(i);
                }
            }
        }
        return result;
    }

    List<Integer> topoSort(int[][] edges, int k) {
        // build graph
        List<List<Integer>> adjacents = new ArrayList<>(k + 1);
        for (int i = 0; i <= k; i++) adjacents.add(new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjacents.get(u).add(v);
        }

        // initialize visited[], 0: not visited, 1 : visiting, 2 : visited
        int[] visited = new int[k + 1];
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            // not visited
            if (visited[i] == 0) {
                List<Integer> tmp = dfs(adjacents, i, visited, result);
                // check cyclic
                if (tmp.isEmpty()) {
                    return Collections.emptyList();
                }
            }
        }

        Collections.reverse(result);
        return result;
    }

    List<Integer> dfs(List<List<Integer>> adjacents, int cur, int[] visited, List<Integer> result) {
        // mark visting
        visited[cur] = 1;
        for (int neighbor : adjacents.get(cur)) {
            // if not visted
            if (visited[neighbor] == 0) {
                List<Integer> tmp = dfs(adjacents, neighbor, visited, result);
                if (tmp.isEmpty()) return Collections.emptyList();
            }
            // if `cur` is in visiting already, => has cycle.
            else if (visited[neighbor] == 1) {
                return Collections.emptyList();
            }
        }

        // mark visted and append result
        visited[cur] = 2;
        result.add(cur);
        return result;
    }


}
