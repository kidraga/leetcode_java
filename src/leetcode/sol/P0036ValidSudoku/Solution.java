package leetcode.sol.P0036ValidSudoku;

import java.util.HashSet;
import java.util.Set;

/**
 * Time: O(N^2) due to nested loop,
 * Space: O(N^2) due to worst case the board is full and all 81 value needs to be saved.
 * where N = 9. Since N is constant, both time and space are technically O(1).
 * But the algorithm also apply to N = any number, so O(N^2) makes more sense.
 */
class Solution {
    public boolean isValidSudoku(char[][] board) {
        int N = 9;

        // Use hash set to record the status
        Set<Character>[] rows = new HashSet[N];
        Set<Character>[] cols = new HashSet[N];
        Set<Character>[] boxes = new HashSet[N];
        for (int r = 0; r < N; r++) {
            rows[r] = new HashSet<Character>();
            cols[r] = new HashSet<Character>();
            boxes[r] = new HashSet<Character>();
        }

        for (int r = 0; r < N; r++) { // O(N) time
            for (int c = 0; c < N; c++) { // O(N) time
                char val = board[r][c];

                // check if the position is filled with number
                if (val == '.') continue;

                // value already shown up before in this row.
                if (rows[r].contains(val)) return false;
                // if value not show before, put it into the row set
                rows[r].add(val);

                // value already shown up before in this column
                if (cols[c].contains(val)) return false;
                // if value not show before, put it into the column set
                cols[c].add(val);

                // check box
                int idx = (r / 3) * 3 + c / 3;
                if (boxes[idx].contains(val)) return false;
                boxes[idx].add(val);
            }
        }

        return true;
    }
}
