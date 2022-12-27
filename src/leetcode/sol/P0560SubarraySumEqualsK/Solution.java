package leetcode.sol.P0560SubarraySumEqualsK;

class Solution {
  /**
   * Brute force solution.
   * find all subarray and calculate it's sum
   */
  public int subarraySum(int[] nums, int k) {
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      int sum = 0;
      for (int j = i; j < nums.length; j++) {
        sum += nums[j];
        if (sum == k) {
          count++;
        }
        // cannot break and quit the inner loop,
        // since num[i] can be 0 or negative
      }
    }
    return count;
  }
}
