package org.terry.leetcode;

import java.util.*;

/*
	Leetcode 310. Minimum Height trees

	The tree/graph here is an undirectional graph. It means that when u -> v, we wll have v -> u also.

	'The height of a rooted tree is the number of edges on the longest downward path between root and leaf'
	-> height of a tree is the longest/maximum number of nodes from root to leaf
	The question's requirement is finding the minimum trees
	-> from a root, we can have multiple trees, and we have to choose the longest among the trees having the same root.
	And from those roots, we have to find the minimum trees.

	Principles:
	- Calculate the height of a tree by using DFS or BFS could take complexity O(pow(n)). 'We should not' follow that way because the constraint is :
	1 <= n <= 10_000*2.
	- Apply the 'Topology' network structure, remove the leaves until we have the rest, aka the root nodes. The rest nodes will be the output we need.
	- After we remove the leaves, the neighbors will lose an edge/neighbor, so we will update 'n' after the leaves removing, the neighbors that having 1 node
	will become the leaves. And we continue until 'n' become <= 2.
 */
public class MinimumHeightTrees {
	public static void main(String[] args) {
		new MinimumHeightTrees().test();
	}

	public void test() {
		int[][] edges;
		int n;
		List<Integer> output;
		List<Integer> actual;
		edges = new int[][]{{1, 0}, {1, 2}, {1, 3}};
		n = 4;
		output = List.of(1);
		actual = findMinHeightTrees(n, edges);
		assert actual.equals(output);

		edges = new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
		n = 6;
		output = List.of(3, 4);
		actual = findMinHeightTrees(n, edges);
		assert actual.equals(output);

		edges = new int[][]{{0, 1}};
		n = 2;
		output = List.of(0, 1);
		actual = findMinHeightTrees(n, edges);
		assert actual.equals(output);

		edges = new int[][]{{0, 1}, {0, 2}, {0, 3}, {3, 4}, {4, 5}};
		n = 6;
		output = List.of(3);
		actual = findMinHeightTrees(n, edges);
		assert actual.equals(output);

	}

	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		// edge case.
		if (n == 1) return List.of(0);
		List<NavigableSet<Integer>> graph = new ArrayList<>(n);
		int[] countNeighbors = new int[n];
		// Initialize the graph.
		for (int i = 0; i < n; i++) graph.add(new TreeSet<>());
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];

			graph.get(u).add(v);
			graph.get(v).add(u);
		}


		// find all the node that having only 1 neighbors, aka leaves in the tree.
		// we use a 'Queue' to store all the leaves.
		Deque<Integer> queue = new LinkedList<>();
		for (int i = 0; i < graph.size(); i++) {
			if (graph.get(i).size() == 1) {
				queue.offer(i);
			}
		}

		// in this step, we poll() every element in the queue and remove it, in paralell, we have to update its neighbor.
		// if neighbor becomes a leaf, we have to offer() it to the 'queue'
		while (!queue.isEmpty()) {
			if (n <= 2) break;
			int currentQueueSize = queue.size();

			// This line is important. We have to update total number of nodes after we removed the 1-neighbor nodes.
			n -= currentQueueSize;
			while (currentQueueSize-- > 0) {
				Integer current = queue.poll();

				// we could find the first right away, because this 'queue' is just storing the 1-neigh nodes. So the fist neighbor
				// will be always the only node.
				NavigableSet<Integer> neighbors = graph.get(current);
				Integer neighbor = neighbors.pollFirst();

				// remove neighbor of current node, and update its neighbors aslo.
				graph.get(neighbor).remove(current);

				// if neighbor becomes a leaf, we offer it to the queue;
				if (graph.get(neighbor).size() == 1) queue.offer(neighbor);

			}
		}

		return new ArrayList<>(queue);
	}
}
