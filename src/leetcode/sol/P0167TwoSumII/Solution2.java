package leetcode.sol.P0167TwoSumII;

class Solution2 {
    public int[] twoSum(int[] numbers, int target) {
        int low = 0;
        int high = numbers.length - 1;
        int[] result = new int[2];
        while (low < high) {
            int sum = numbers[low] + numbers[high];

            if (sum == target) {
                result[0] = low + 1;
                result[1] = high + 1;
                return result;
            } else if (sum < target) {
                low++;
            } else {
                high--;
            }
        }
        result[0] = -1;
        result[1] = -1;
        return result;
    }
}
