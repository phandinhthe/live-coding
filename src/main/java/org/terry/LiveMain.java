package org.terry;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LiveMain {


	public static void main(String[] args) {
		new LiveMain().test();
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
			if (diff > 0) return 1;
			else if (diff < 0) return -1;
			return 0;
		});

		double res = Double.MAX_VALUE;
		QualifiedGroup qualifiedGroup = new QualifiedGroup(k);
		for (Worker worker : workers) {
			qualifiedGroup.offer(worker);
			if (qualifiedGroup.size() == k) {
				res = Math.min(res, qualifiedGroup.totalQuantity * worker.ratio);
			}
		}
		return res;
	}

	private static class Worker {
		private double ratio;
		private int quantity;

		public Worker(double ratio, int quantity) {
			this.ratio = ratio;
			this.quantity = quantity;
		}
	}

	private static class QualifiedGroup {
		private final int capacity;
		private final PriorityQueue<Worker> pQueue;
		private int totalQuantity;
		public QualifiedGroup(int size) {
			this.capacity = size;
			this.pQueue = new PriorityQueue<>((w1, w2) -> {
				double diff = w1.quantity - w2.quantity;
				if (diff > 0) return -1;
				if (diff < 0) return 1;
				return 0;
			});
		}

		public int size() {
			return pQueue.size();
		}

		public void offer(Worker worker) {
			if (pQueue.size() == capacity) poll();
			pQueue.offer(worker);
			totalQuantity += worker.quantity;
		}

		public void poll() {
			assert !pQueue.isEmpty();
			totalQuantity -= pQueue.poll().quantity;
		}
	}

}