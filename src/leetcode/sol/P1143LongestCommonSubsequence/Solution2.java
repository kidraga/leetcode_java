package leetcode.sol.P1143LongestCommonSubsequence;

/**
 * Time: O(M*N^2), M = text1.length, N = text2.length
 * The text2.indexOf() gives an additional O(N)
 * Space: O(M*N)
 */
class Solution2 {
    private String text1;
    private String text2;
    private int[][] memo;
    public int longestCommonSubsequence(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
        // make the memo big enough to hold the cases where the pointers
        // go over the edges of the strings
        memo = new int[text1.length() + 1][text2.length() + 1];
        // we need to initialise the memo array to -1 so that we know
        // whether or not a value has been filled in. Keep the base cases
        // as 0's to simplify the later code a bit
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                this.memo[i][j] = -1;
            }
        }
        return memoSolve(0, 0);
    }

    private int memoSolve(int p1, int p2) {
        // check whether we've already solved this subproblem.
        // This also covers the base cases where p1 == text1.length
        // or p2 == text2.length
        if (memo[p1][p2] != -1) {
            return memo[p1][p2];
        }

        // option 1: we don't include text1[p1] in the solution
        int option1 = memoSolve(p1 + 1, p2);

        // option 2: we include text1[p1] in the solution, as long as
        // a match for it in text2 at or after p2 exists.
        int firstOccurrence = text2.indexOf(text1.charAt(p1), p2);
        // String.indexOf(char, startIndex), return the index of char in the string, starting from the given startIndex
        // return -1 if char is not found
        int option2 = 0;
        if (firstOccurrence != -1) {
            option2 = 1 + memoSolve(p1 + 1, firstOccurrence + 1);
        }

        // Add the best answer to the memo before returning it.
        memo[p1][p2] = Math.max(option1, option2);
        return memo[p1][p2];
    }

}
