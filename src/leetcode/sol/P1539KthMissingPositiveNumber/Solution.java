package leetcode.sol.P1539KthMissingPositiveNumber;

/**
 * Brute force
 * Time: O(n)
 * Space: O(1)
 */
public class Solution {
    public int findKthPositive(int[] arr, int k) {
        // arr[0] is the smallest
        // if k is smaller than that, no need to search and k is the answer
        if (k <= arr[0] - 1) return k;

        k -= arr[0] - 1;

        // search kth missing between the array numbers
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            // missing between arr[i] and arr[i+1]
            int currMissing = arr[i + 1] - arr[i] - 1;
            // if the kth missing is between
            // arr[i] and arr[i+1], return it
            if (k <= currMissing) {
                return arr[i] + k;
            }
            // otherwise, continue
            k -= currMissing;
        }

        // the missing number is greater than arr[n-1]
        return arr[n - 1] + k;
    }
}
