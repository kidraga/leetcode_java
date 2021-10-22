package leetcode.sol.P0036ValidSudoku;

public class Solution2 {
    /**
     * Similar to solution 1, instead of using a set: {0, 1, ..., 9}
     * we use an array that has length 9: [0, 0, ..., 0]
     * While we scan the board, if a number exist, we mark the corresponding
     * position (= number - 1) as 1.
     * For example, if a row has number 1, 4, 8, its array looks like:
     * [1, 0, 0, 1, 0, 0, 0, 1, 0]
     *  1, 2, 3, 4, 5, 6, 7, 8, 9
     *
     *  During the check, if we use set, the set.contains(x) is O(1).
     *  If we use array, the array[x - 1] == 1 is also O(1).
     *
     *  We can almost see that this 1 and 0 array can be replaced by a binary number,
     *  which is solution 3.
     *
     *  Note: how to calculate distance between char?
     *  '3' - '1' = 2; char - char = int
     */
    public boolean isValidSudoku(char[][] board) {
        int N = 9;

        // use array to store if value exist
        int[][] rows = new int[N][N];
        int[][] cols = new int[N][N];
        int[][] boxes = new int[N][N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                char val = board[r][c];

                // if no value, skip
                if (val == '.') continue;

                // calculate index
                int pos = board[r][c] - '1';

                // check row
                if (rows[r][pos] == 1) return false;
                // mark the position as 1 since we've seen this number
                rows[r][pos] = 1;

                // do the same for column
                if (cols[c][pos] == 1) return false;
                cols[c][pos] = 1;

                // do the same for box
                int idx = (r / 3) * 3 + c / 3;
                if (boxes[idx][pos] == 1) return false;
                boxes[idx][pos] = 1;
            }
        }
        return true;
    }
}
