package org.terry;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LiveMain {
    public static void main(String[] args) {
        new LiveMain().test();
    }
    public void test() {


	}
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        List<Project> projects = new ArrayList<>(profits.length);
        for (int i = 0; i < profits.length; i++) {
            projects.add(new Project(capital[i], profits[i]));
        }
        projects.sort(new AscCapitalComparator());
        int i = 0;
        PriorityQueue<Project> pQueue = new PriorityQueue<>(new DscProfitComparator());
        int count = 0;
        int res = w;
        while (i < profits.length) {
            while (i < profits.length && projects.get(i).capital <= w) {
                pQueue.offer(projects.get(i++));
            }
            if (pQueue.isEmpty()) return res;
            int requiredCapital = projects.get(Math.min(i, profits.length - 1)).capital;
            while (w < requiredCapital && !pQueue.isEmpty()) {
                int val = pQueue.poll().profit;
                w += val;
                res += val;
                count++;
                if (count == k)
                    return res;
            }
        }
        while (count < k && !pQueue.isEmpty()) {
            res += pQueue.poll().profit;
            count++;
        }
        return res;
    }

    static class Project {
        int capital;
        int profit;

        public Project(int capital, int profit) {
            this.capital = capital;
            this.profit = profit;
        }
    }

    static class AscCapitalComparator implements Comparator<Project> {
        public int compare(Project p1, Project p2) {
            return p1.capital - p2.capital;
        }
    }

    static class DscProfitComparator implements Comparator<Project> {
        public int compare(Project p1, Project p2) {
            return -p1.profit + p2.profit;
        }
    }
}
