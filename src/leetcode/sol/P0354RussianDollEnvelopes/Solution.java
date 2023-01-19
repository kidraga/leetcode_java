package leetcode.sol.P0354RussianDollEnvelopes;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 和官方答案的思路一样,只是LIS慢一些所以TLEl
 */
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        // 按w升序排列,如果w一样,则按h降序排列
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] == b[0]
                    ? b[1] - a[1]
                    : a[0] - b[0];
            }
        });

        // 对高度数组寻找LIS
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthOfLIS(height);
    }

    // leetcode 300
    private int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        // base case
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
