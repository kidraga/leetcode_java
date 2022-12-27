package leetcode.sol.P0045JumpGateII;

class Solution4 {
    public int jump(int[] nums) {
        int steps = 0, curLevelEnd = 0, nxtLevelEnd = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > curLevelEnd) {
                steps++;
                curLevelEnd = nxtLevelEnd;
            }
            nxtLevelEnd = Math.max(nxtLevelEnd, i + nums[i]);
        }
        return steps;
    }
}
