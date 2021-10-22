package leetcode.sol.P0036ValidSudoku;

public class Solution3 {
    /**
     * Similar to solution2, instead of using an array, we use a binary number.
     * [1, 0, 0, 1, 0, 0, 0, 1, 0] -> 1,4,8 is seen in a row, col or box
     * 100100010 -> binary representation (aka bit map).
     * This way we save some space, since int[N] is O(N) but a number is O(1).
     *
     * So we need 9 bits to represent a row.
     * How do we pick a bit? using &
     * E.g. we want to pick 3rd bit: xxx xxx xxx & 000 000 100 = 000 000 x00
     * then we can check if 000 000 x00 is > 0 to know if x == 1
     *
     * How to construct a binary that has 3rd bit as 1?
     * 1 << (3 - 1)
     *
     * How to mark a bit to 1?
     * xxx xxx xxx | 000 000 100 = xxx xxx 1xx
     *
     * Why this works? How do I know that bit map is a possible solution?
     * It's easier to get to bit map solution if you can think of solution 1 or 2.
     * But in case you didn't, the hint is that you want to "check whether you've seen
     * some unique items".
     * There are two prerequisite here:
     * 1. the values are unique
     * 2. each of them has a binary status: seen, not seen.
     * Because of 1, we can use the position of the bit to represent that element.
     * Because of 2, states can be reflected by 1 and 0.
     */
    public boolean isValidSudoku(char[][] board) {
        int N = 9;

        // use a binary number to save the states of a row, col or box
        int[] rows = new int[N];
        int[] cols = new int[N];
        int[] boxes = new int[N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c] == '.') continue;
                int val = board[r][c] - '0';
                int bitPos = 1 << (val - 1); // e.g. 3 -> 0100

                // check rows
                if ((rows[r] & bitPos) > 0) return false;
                // mark that bit as 1
                rows[r] = rows[r] | bitPos;

                // do the same for cols
                if ((cols[c] & bitPos) > 0) return false;
                cols[c] = cols[c] | bitPos;

                // do the same for boxes
                int idx = (r / 3) * 3 + c / 3;
                if ((boxes[idx] & bitPos) > 0) return false;
                boxes[idx] = boxes[idx] | bitPos;
            }
        }
        return true;
    }
}
