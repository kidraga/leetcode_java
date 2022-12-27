package leetcode.sol.P0051NQueues;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.tools.DiagnosticCollector;

class Solution {

    private int size;
    private List<List<String>> solutions = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        size = n;
        char[][] emptyBoard = new char[size][size];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                emptyBoard[i][j] = '.';
            }
        }

        backtrack(0, new HashSet<>(), new HashSet<>(), new HashSet<>(), emptyBoard);
        return solutions;
    }

    private void backtrack(
            int row, 
            Set<Integer> diagonals,
            Set<Integer> antiDiagonals,
            Set<Integer> cols,
            char[][] state
    ) {
        // base case: N queens have been placed
        if (row == size) {
            solutions.add(createBoard(state));
            return;
        }

        for (int col = 0; col < size; col++) {
            int currDiagonal = row - col;
            int currAntiDiagonal = row + col;

            // if queen is not placeable
            if (cols.contains(col)
                    || diagonals.contains(currDiagonal)
                    ||antiDiagonals.contains(currAntiDiagonal)
            ) {
                continue;
            }

            // Add the queen to the board
            cols.add(col);
            diagonals.add(currDiagonal);
            antiDiagonals.add(currAntiDiagonal);
            state[row][col] = 'Q';

            // move on to the next row with the updated board state
            backtrack(row + 1, diagonals, antiDiagonals, cols, state);

            // remove the queen we just placed.
            cols.remove(col);
            diagonals.remove(currDiagonal);
            antiDiagonals.remove(currAntiDiagonal);
            state[row][col] = '.';
        }
    }

    // output the board to the correct format
    private List<String> createBoard(char[][] state) {
        List<String> board = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            String currentRow = new String(state[row]);
            board.add(currentRow);
        }
        return board;
    }

}
