package leetcode.sol.P0713SubarrayProductLessThanK;

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) return 0;
        double logk = Math.log(k);
        double[] prefix = new double[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i+1] = prefix[i] + Math.log(nums[i]);
        }

        int ans = 0;
        for (int i = 0; i < prefix.length; i++) {
            int low = i + 1, high = prefix.length;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (prefix[mid] < prefix[i] + logk - 1e-9) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            ans += low - i - 1;
        }
        return ans;
    }
}
