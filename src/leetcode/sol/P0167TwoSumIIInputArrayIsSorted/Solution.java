package leetcode.sol.P0167TwoSumIIInputArrayIsSorted;
import java.util.Arrays;

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers.length == 2) {
            return new int[]{1, 2};
        }

        for (int i = 0; i < numbers.length - 1; i++) {
            int remain = target - numbers[i];
            int remainIndex = findRemainIndex(Arrays.copyOfRange(numbers, i + 1, numbers.length), remain);
            if (remainIndex != -1) {
                return new int[]{i + 1, remainIndex + i + 2};
            }
        }
        return null;
    }

    private int findRemainIndex(int[] numbers, int remain) {
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] == remain) {
                return mid;
            } else if (numbers[mid] > remain) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (left >= 0 && left < numbers.length && numbers[left] == remain) {
            return left;
        }
        if (right >= 0 && right < numbers.length && numbers[right] == remain) {
            return right;
        }
        return -1;
    }

}
