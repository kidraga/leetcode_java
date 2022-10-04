package leetcode.sol.P09233SumWithMultiplicity;

import java.util.Arrays;

public class Solution3 {
    public int threeSumMulti(int[] arr, int target) {
        int MOD = 1_000_000_007;
        long ans = 0;
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            int newTarget = target - arr[i];
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                if (arr[left] + arr[right] < newTarget) {
                    left++;
                } else if (arr[left] + arr[right] > newTarget) {
                    right--;
                } else if (arr[left] != arr[right]) {
                    // let's count "left": the number of arr[left] == arr[left+1] == arr[left+2] ...
                    // and similarly for "right"
                    int leftCount = 1;
                    while (left + 1 < right && arr[left] == arr[left + 1]) {
                        leftCount++;
                        left++;
                    }
                    int rightCount = 1;
                    while (right - 1 > left && arr[right] == arr[right - 1]) {
                        rightCount++;
                        right--;
                    }
                    ans += leftCount * rightCount;
                    ans %= MOD;
                    left++;
                    right--;
                } else {
                    // M = right - left + 1
                    // we contributed M * (M-1) / 2 pairs.
                    ans += (right - left + 1) * (right - left) / 2;
                    ans %= MOD;
                    break;
                }
            }
        }
        return (int) ans;
    }
}
