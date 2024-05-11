package org.terry.leetcode;


import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/minimum-cost-to-hire-k-workers
 * <p>
 * Principal for solutions:
 * - Find the 'ratio' of every worker (wage[i] / quality[i]).
 * - Sort the worker array by its 'ratio'.
 * => to find the minimum cost, we just count the workers having 'ratio' lesser or equal to the worker[i]. Repeat till the last worker in sorted array.
 * =>	Apply a window (size = k), move from i = 0 till the end, only when window size = k, we track the min value of 'ratio of current worker' * total quality in window.
 * When we at the next worker, we have to substract the largest-quality worker in the window and append the next worker quality.
 */
public class MinCostToHireKWorkers {
	public static void main(String[] args) {
		new MinCostToHireKWorkers().test();
	}
	public void test() {
		int[] quality;
		int[] wage;
		int k;
		double output;
		double actual;

		quality = new int[]{10, 20, 5};
		wage = new int[]{70, 50, 30};
		k = 2;
		output = 105.00000F;
		actual = mincostToHireWorkers(quality, wage, k);
		Assertions.assertEquals(output, actual, 10_00000);

		quality = new int[]{3, 1, 10, 10, 1};
		wage = new int[]{4, 8, 2, 2, 7};
		k = 3;
		output = 30.66667F;
		actual = mincostToHireWorkers(quality, wage, k);
		Assertions.assertEquals(output, actual, 10_000);

	}

	private static final double DECIMAL = 1.0F;

	public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
		Worker[] workers = new Worker[quality.length];

		for (int i = 0; i < quality.length; i++) {
			workers[i] = new Worker(DECIMAL * wage[i] / quality[i], quality[i]);
		}

		Arrays.sort(workers, (w1, w2) -> {
			double diff = w1.ratio - w2.ratio;
			if (diff > 0)
				return 1;
			else if (diff < 0)
				return -1;
			return 0;
		});

		double res = Double.MAX_VALUE;
		QualifiedGroup qualifiedGroup = new QualifiedGroup(k);
		for (Worker worker : workers) {
			qualifiedGroup.offer(worker);
			if (qualifiedGroup.size() == k) {
				res = Math.min(res, qualifiedGroup.totalQuality * worker.ratio);
			}
		}
		return res;
	}

	/**
	 * Worker
	 * To keep track of ratio, quality values.
	 */
	private static class Worker {
		private double ratio;
		private int quality;

		public Worker(double ratio, int quality) {
			this.ratio = ratio;
			this.quality = quality;
		}
	}

	/**
	 * QualifiedGroup
	 * Window, include all the worker as the current qualified candidates.
	 * keep track of size (k).
	 * alway maintain the size == k, if size > k, we must remove the largest-quality worker and update the total quality in window.
	 */
	private static class QualifiedGroup {
		private final int capacity; // initial size of the window. (similar to 'k')
		private final PriorityQueue<Worker> pQueue;
		private int totalQuality;// the maintained total quality of all the workers.

		public QualifiedGroup(int size) {
			this.capacity = size;
			this.pQueue = new PriorityQueue<>((w1, w2) -> {
				double diff = w1.quality - w2.quality;
				if (diff > 0)
					return -1;
				if (diff < 0)
					return 1;
				return 0;
			});
		}

		public int size() {
			return pQueue.size();
		}

		// add new worker to window
		// maintain totalQuality also
		public void offer(Worker worker) {
			if (pQueue.size() == capacity)
				poll();
			pQueue.offer(worker);
			totalQuality += worker.quality;
		}

		// remove the largest-quality worker in the window
		// maintain totalQuality also
		public void poll() {
			assert !pQueue.isEmpty();
			totalQuality -= pQueue.poll().quality;
		}
	}

}
