package leetcode.sol.P0072EditDistance;

class Solution {

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        if (m == 0) return n;
        if (n == 0) return m;

        int dp[][] = new int[m + 1][n + 1];

        // base case
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int option1 = dp[i - 1][j] + 1;
                    int option2 = dp[i][j - 1] + 1;
                    int option3 = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(option1, Math.min(option2, option3));
                }
            }
        }
        return dp[m][n];
    }
}
