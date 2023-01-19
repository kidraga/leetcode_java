
package leetcode.sol.P0354RussianDollEnvelopes;

import java.util.Arrays;
import java.util.Comparator;

class Solution2 {
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

    private int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        // base case
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = - (i + 1);
            }
            dp[i] = num;
            if (i == len) len++;
        }
        return len;
    }
}
