package leetcode.sol.P0718MaximumLengthOfRepeatedSubarray;

class Solution {
    /**
     * 思路参考：https://leetcode.com/problems/maximum-length-of-repeated-subarray/solutions/109059/O(mn)-time-O(1)-space-solution/
     * 把两个array重叠在一起，然后loop一遍看看重合的subarray有多长
     *          [1,2,3,2,1]   --> 
     *           <--    [3,2,1,4,7]  
     *
     *          [1,2,3,2,1]    
     *                [3,2,1,4,7]  
     *
     *          [1,2,3,2,1]      -->
     *     <--      [3,2,1,4,7]
     */
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int res = 0;

        // offset only needs to be applied to traversing index of nums2
        for (int offset = -n + 1; offset <= m - 1; offset++) {
            // 记录当前重合subarray的长度
            int currSubarrayLen = 0;
            for (int i = 0; i < m + n - 1; i++) {
                int aIndex = i;
                int bIndex = i - offset; // minus, not plus

                if (aIndex >= 0 && aIndex < m && bIndex >= 0 && bIndex < n) {
                    if (nums1[aIndex] == nums2[bIndex]) {
                        currSubarrayLen++;
                        res = Math.max(res, currSubarrayLen);
                    } else {
                        currSubarrayLen = 0;
                    }
                }
            }
        }
        return res;
    }
}
