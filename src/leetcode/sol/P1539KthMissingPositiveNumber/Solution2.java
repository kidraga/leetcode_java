package leetcode.sol.P1539KthMissingPositiveNumber;

public class Solution2 {
    /**
     *  0,1,2,3,4 index
     * [2,3,4,7,11]
     * 1,2,3,6,10 supposed index = arr[i] - 1
     * 以7为例，7的index是3而不是6的原因是前面缺了3个数，即缺了6-3个数
     * 也就是说在任意位置上，你可以通过arr[i] - 1 - i来知道前面少了几个数，记做missingNumberCount
     * 上面的例子可以看出，会出现多个element的missingNumberCount相同，比如2,3,4，都缺1
     * 假如k=1,答案则应该return 1，在4的位置上，我们知道需要返回的值一定在左边，因为arr[2] - 1 - 2 = 1
     * 假如k=2，答案return 5, 在4的位置上，missingNumberCount=1, 1 < k=2，那么我们知道要返回的值不在4的左边
     * 二分法取index的中值mid，求出arr[mid] - 1 - mid
     * 如果这个值比k小，说明目标在右侧
     */
    public int findKthPositive(int[] arr, int k) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // if number of positive integers
            // which are missing before arr[mid]
            // is less than k,
            // continue to search on the right
            int missingNumberCount = arr[mid] - mid - 1;
            if (missingNumberCount < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // at the end of the loop, left = right + 1,
        // and the kth missing is in-between arr[right] and arr[left].
        // The number of integers missing before arr[right] is
        // arr[right] - right - 1,
        // the number to return is
        // arr[right] + k - (arr[right] - right - 1) = k + left
        return left + k;
    }
}
