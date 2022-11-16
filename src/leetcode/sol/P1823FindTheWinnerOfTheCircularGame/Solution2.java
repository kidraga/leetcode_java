package leetcode.sol.P1823FindTheWinnerOfTheCircularGame;

public class Solution2 {
    public int findTheWinner(int n, int k) {
        return dp(n, k) + 1; // +1 for converting 0-based to 1-based
    }

    private int dp(int n, int k) {
        if (n == 1) return 0;
        return (dp(n - 1, k) + k) % n;
    }
}