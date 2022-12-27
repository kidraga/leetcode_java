package leetcode.sol.P0045JumpGateII;

/**
 * Brute force with memorization.
 */
class Solution2 {
    Integer[] mem;
    public int jump(int[] nums) {
        mem = new Integer[nums.length];
        return jump(0, nums);
    }

    private int jump(int pos, int[] nums) {
        int n = nums.length;

        // Base condition
        if (pos >= n - 1) {
            return 0;
        }

        if (mem[pos] != null) {
            return mem[pos];
        }

        int minSteps = n;
        for (int i = 1; i <= nums[pos]; i++) {
            minSteps = Math.min(minSteps, 1 + jump(pos + i, nums));
        }

        return mem[pos] = minSteps;
    }
}
