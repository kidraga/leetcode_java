package leetcode.sol.P1094CarPooling;

// same as 370
class Difference {
    private int[] diff;

    public Difference(int[] nums) {
        assert nums.length > 0;
        diff = new int[nums.length];
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
    }

    public void increment(int i, int j, int val) {
        diff[i] += val;
        if (j + 1 < diff.length) {
            diff[j + 1] -= val;
        }
    }

    public int[] result() {
        int[] res = new int[diff.length];
        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }
}
public class Solution {
    public  boolean carPooling(int[][] trips, int capacity) {
        // 最多有1001个车站
        int[] nums = new int[1001];

        Difference df = new Difference(nums);

        for (int[] trip : trips) {
            // 乘客数量
            int val = trip[0];
            // 第trip[1]站乘客上车
            int i = trip[1];
            // 第trip[2]站乘客下车
            // 乘客在车上的区间是[trip[1], trip[2] - 1]
            int j = trip[2] - 1;
            df.increment(i, j, val);
        }

        int[] res = df.result();

        // check是否超载过
        for (int i = 0; i < res.length; i++) {
            if (capacity < res[i]) return false;
        }
        return true;
    }
}
