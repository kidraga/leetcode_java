package leetcode.sol.P0200NumberOfIslands;

import java.util.Deque;
import java.util.LinkedList;

class Solution2 {
    public int numIsLands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numberOfRows = grid.length;
        int numberOfColums = grid[0].length;
        int numberOfIslands = 0;

        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColums; col++) {
                if (grid[row][col] == '1') {
                    numberOfIslands++;
                    grid[row][col] = '0';
                    Deque<Integer> neighbors = new LinkedList<>();
                    neighbors.offer(row * numberOfColums + col); // index of current node
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.poll();
                        int currRow = id / numberOfColums; // get row
                        int currCol = id % numberOfColums; // get col

                        // loop through all current level nodes and put all connected next level nodes to queue
                        // So it's BFS
                        // add left node if left node is in bound and is '1'
                        if (currRow - 1 >= 0 && grid[currRow - 1][currCol] == '1') {
                            neighbors.offer((currRow - 1) * numberOfColums + currCol);
                            grid[currRow - 1][currCol] = '0';
                        }
                        // add right node if right node is in bound and is '1'
                        if (currRow + 1 < numberOfRows && grid[currRow + 1][currCol] == '1') {
                            neighbors.offer((currRow + 1) * numberOfColums + currCol);
                            grid[currRow + 1][currCol] = '0';
                        }
                        // add upper node if upper node is in bound and is '1'
                        if (currCol - 1 >= 0 && grid[currRow][currCol - 1] == '1') {
                            neighbors.offer(currRow * numberOfColums + currCol - 1);
                            grid[currRow][currCol - 1] = '0';
                        }
                        // add lower node if lower node is in bound and is '1'
                        if (currCol + 1 < numberOfColums && grid[currRow][currCol + 1] == '1') {
                            neighbors.offer(currRow * numberOfColums + currCol + 1);
                            grid[currRow][currCol + 1] = '0';
                        }
                    }
                }
            }
        }
        return numberOfIslands;
    }
}
