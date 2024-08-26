package leetcode.sol.P1885CountPairsInTwoArrays;

import java.util.Arrays;

class Solution {
    // https://leetcode.com/problems/count-pairs-in-two-arrays/solutions/5125899/java-solution-detailed-explanation
    // nums1[i] + nums1[j] > nums2[i] + nums2[j] 等同于 (nums1[i] - nums2[i]) + (nums1[j] - nums2[j]) > 0
    // imply我们可以将两个array做diff=nums1 - nums2. 上面的长式子就变成了diff[i] + diff[j] > 0
    // 另外一个特性是if a+b >=0, then c+b>=0 if c>=a
    // 也就是说sort diff可能会有用
    //
    // 1. diff=nums1-nums2
    // 2. sort diff
    // 3. get two pointers. left = 0, right = last in diff
    // 4. when right > left
    // 4.1 fix right, check if diff[left] + diff[right] <= 0. If it is, means we need to move left pointer to right to make it larger
    // 4.2 if the two sum > 0, any number in between left and right will be correct. So answer+= right - left. Then we move right pointer to left
    public long countPairs(int[] nums1, int[] nums2) {
        int[] diff = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            diff[i] = nums1[i] - nums2[i];
        }

        Arrays.sort(diff);

        int right = diff.length - 1;
        int left = 0;
        long ans = 0;

        while (right > left) {
            while (right > left && diff[right] + diff[left] <= 0) {
                left++;
            }

            if (left == right) {
                break;
            } else {
                ans = ans + right - left;
                right--;
            }
        }
        return ans;
    }
}
