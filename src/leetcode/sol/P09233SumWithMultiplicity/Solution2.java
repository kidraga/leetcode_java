package leetcode.sol.P09233SumWithMultiplicity;

import java.util.Arrays;

/**
 * Wrong answer
 * 找到符合条件的组合之后，不可以left++,right++。
 * 例如[2,2,3,4,4], 8
 * 2,2,4,4可以出两个组合，但是因为左右指针一起缩了，所以就错过了。
 */
class Solution2 {
    public int threeSumMulti(int[] arr, int target) {
        Arrays.sort(arr);

        int ans = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            int newTarget = target - arr[i];
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int leftNum = arr[left];
                int rightNum = arr[right];
                if (leftNum + rightNum == newTarget) {
                    ans++;
                    left++;
                    right--;
                } else if (leftNum + rightNum > newTarget) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }
}
