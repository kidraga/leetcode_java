package leetcode.sol.P0200NumberOfIslands;

public class Solution {

    /**
     * DFS solution
     */
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int numberOfRows = grid.length;
        int numberOfColumns = grid[0].length;
        int numberOfIslands = 0;

        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                if (grid[row][col] == '1') {
                    numberOfIslands++;
                    recursivelyFlipAllConnectedLand(grid, row, col);
                }
            }
        }

        return numberOfIslands;
    }

    private void recursivelyFlipAllConnectedLand(char[][] grid, int row, int col) {
        int numberOfRows = grid.length;
        int numberOfColumns = grid[0].length;

        if (row < 0 || row >= numberOfRows || col < 0 || col >= numberOfColumns || grid[row][col] == '0') {
            return;
        }

        grid[row][col] = '0';
        recursivelyFlipAllConnectedLand(grid, row + 1, col);
        recursivelyFlipAllConnectedLand(grid, row - 1, col);
        recursivelyFlipAllConnectedLand(grid, row, col + 1);
        recursivelyFlipAllConnectedLand(grid, row, col - 1);
    }
}
