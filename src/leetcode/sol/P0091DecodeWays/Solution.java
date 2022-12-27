package leetcode.sol.P0091DecodeWays;

class Solution {
    public int numDecodings(String s) {
        /**
         * dp(i) number of ways to decode at ith position
         * You have a few options:
         * 1. Consider s[i] as a letter on its own. s[i] is valid if it's not '0'
         * If valid, then the whole string s[0:i] continued to be valid,
         * so we have the same number of ways to decode as dp(i-1).
         * If not valid, then number of ways to decode is 0.
         * 2. Consider s[i-1]s[i].
         *
         * 222, 3
         * 22 is 2
         * 2,2,2
         * 22, 2
         * 2,22
         *
         * 2226 is dp(3) = 5 = dp(1) + dp(2)
         * 22 is dp(1) = 2
         * 222 is dp(2) = 3
         * 2,2,2,6
         * 22,2,6
         * 2,22,6
         * 22,26
         * 2,2,26
         *
         * 2200 dp(3) = 0 = dp(1) * 0 + dp(2) * 0
         * 22 is dp(1) = 2
         * 220 is dp(2) = 1 -> 2,20
         * 2200 is 0 -> 0, 00 both invalid
         *
         * 2202 dp(3) = 1 = dp(1) * 0 + dp(2) * 1
         * 22 is dp(1) = 2
         * 220 is dp(2) = 1
         * 2202 is 1 -> 220, 2 is 1, 22,02 is 0
         *
         * 12 dp(1) = 2 =
         * 1 dp(0) = 1
         * 1,2 dp(1) = dp(-1) + dp(0)
         *
         * dp(i) = dp(i-1) * (isValidOneDigit) + dp(i-2) * (isValidTwoDigit)
         */

        if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;

        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            int useSingle = 0;
            int useDouble = 0;

            char num = s.charAt(i);
            // consider num as one letter
            if (num != '0') {
                useSingle = dp[i-1];
            }

            char prevTwoNum = s.charAt(i-1);
            if (prevTwoNum == '1' || (prevTwoNum == '2' && num >= '0' && num <= '6')) {
                if (i - 2 >= 0) {
                    useDouble = dp[i-2];
                } else {
                    useDouble = 1;
                }
            }
            dp[i] = useSingle + useDouble;
        }

        return dp[dp.length-1];
    }
}
