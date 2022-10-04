package leetcode.sol.P1091ShortestPathInBinaryMatrix;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Override each visited node to 1, so we don't need to visit again
 */
public class Solution {
    private static final int[][] directions = 
        new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return -1;
        // check start and end must be 0
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
            return -1;
        }

        Deque<int[]> queue = new ArrayDeque<>();
        grid[0][0] = 1; // override starting point
        queue.add(new int[]{0, 0}); // {row, col}
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];
            int distance = grid[row][col]; // we have marked the distance from last level
            if (row == grid.length - 1 && col == grid[0].length -1) {
                return distance;
            }
            // get all next level nodes
            for (int[] neighbour : getNeighbours(row, col, grid)) {
                int neighbourRow = neighbour[0];
                int neighbourCol = neighbour[1];
                queue.add(new int[]{neighbourRow, neighbourCol}); // add node to queue
                grid[neighbourRow][neighbourCol] = distance + 1; // override value to it's distance to starting point
            }
        }
        return -1;
    }

    private List<int[]> getNeighbours(int row, int col, int[][] grid) {
        List<int[]> neighbours = new ArrayList<>();
        for (int i = 0; i < directions.length; i++) {
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length
                    || grid[newRow][newCol] != 0) {
                continue;
            }
            neighbours.add(new int[]{newRow, newCol});
        }
        return neighbours;
    }
}
