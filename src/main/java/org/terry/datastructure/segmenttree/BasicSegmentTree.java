package org.terry.datastructure.segmenttree;


import java.util.List;

// Research segment tree.
public class BasicSegmentTree {

  public static void main(String[] args) {


  }

  private class SegmentTree {

    private final int size;
    private int[] sum;
    private List<Integer> numbers;

    SegmentTree(int size, List<Integer> numbers) {
      this.size = size * 4;
      this.sum = new int[this.size];
      this.numbers = numbers;
    }

    SegmentTree construct() {
      buildSum(1, 0, numbers.size() - 1);
      return this;
    }

    private void add(int pos, int value) {
      numbers.add(pos, value);
      buildSum(1, 0, numbers.size() - 1);

    }

    private int buildSum(int index, int left, int right) {
      if (left == right) {
        return sum[index] = numbers.get(left);
      }

      int mid = (left + right) / 2;
      return sum[index] = buildSum(2 * index, left, mid) + buildSum(2 * index + 1, mid + 1, right);
    }

    private int findSum(int left, int right) {
      return findSum(1, left, right, 0, numbers.size() - 1);
    }

    private int findSum(int index, int left, int right, int treeLeft, int treeRight) {
      if (treeRight < left || right < treeLeft) {
        return 0;
      }

      if (left <= treeLeft && treeRight <= right) {
        return sum[index];
      }
      int mid = (treeLeft + treeRight) / 2;
      return findSum(index * 2, left, right, treeLeft, mid) + findSum(index * 2 + 1, left, right,
          mid + 1, treeRight);
    }

    private int findSum(int index, int treeLeft, int treeRight, int value) {
      if (sum[index] == value) {
        return treeLeft;
      }
      if (treeLeft == treeRight) {
        return treeLeft;
      }
      int mid = (treeLeft + treeRight) / 2;

      return findSum(2 * index + 1, mid + 1, treeRight, value);

    }
  }
}
