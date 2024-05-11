package org.terry.algo.binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Binary {

  public static void main(String[] args) {
//    System.out.println(
//        new Binary().threeSumClosest(new int[]{0,3,97,102,200}, 300)
//    );

    Integer[] arr = new Integer[]{1, 1, 1, 3, 4, 4, 5, 5, 7};

    Integer val = Integer.valueOf(6);

    List<Integer> ans = new ArrayList<>(Arrays.asList(arr));
    ans.add(new Binary().upperBound(arr, val), val);
    ans.add(new Binary().lowerBound(arr, 5), 5);

    System.out.println(ans);
  }

  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    int sum = nums[0] + nums[1] + nums[2];
    int ans = sum;
    int min = Math.abs(sum - target);

    for (int i = 0; i < nums.length - 2; i++) {
      for (int j = i + 1; j < nums.length - 1; j++) {
        for (int k = j + 1; k < nums.length; k++) {
          sum = nums[i] + nums[j] + nums[k];
          int tmp = Math.abs(sum - target);

          if (tmp == 0) {
            return sum;
          }
          if (tmp < min) {
            min = tmp;
            ans = sum;
          }
          if (tmp > min && sum > target) {
            break;
          }
        }
      }
    }
    return ans;
  }

  int upperBound(Integer[] arr, int val) {
    int ans = arr.length;
    int left = 0;
    int right = arr.length - 1;

    while (left <= right) {
      int mid = (left + right) / 2;

      if (arr[mid] <= val) {
        left = mid + 1;
      } else {
        ans = mid;
        right = mid - 1;
      }
    }

    return ans;
  }

  int lowerBound(Integer arr[], int val) {
    int ans = -1;
    int left = 0;
    int right = arr.length - 1;

    while (left <= right) {
      int mid = (left + right) / 2;
      if (val <= arr[mid]) {
        ans = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    return ans;
  }
}