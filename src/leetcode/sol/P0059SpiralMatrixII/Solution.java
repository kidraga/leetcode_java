package leetcode.sol.P0059SpiralMatrixII;

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int upper_bound = 0;
        int lower_bound = n - 1;
        int left_bound = 0;
        int right_bound = n - 1;
        // 需要填入的数字
        int num = 1;

        while (num <= n * n) {
            if (upper_bound <= lower_bound) {
                // 在顶部从左向右遍历
                for (int j = left_bound; j <= right_bound; j++) {
                    matrix[upper_bound][j] = num;
                    num++;
                }
                // upper_bound下移
                upper_bound++;
            }

            if (left_bound <= right_bound) {
                // 在右侧从上向下遍历
                for (int i = upper_bound; i <=lower_bound; i++) {
                    matrix[i][right_bound] = num;
                    num++;
                }
                // right_bound左移
                right_bound--;
            }

            if (upper_bound <= lower_bound) {
                // 在底部从右向左遍历
                for (int j = right_bound; j >= left_bound; j--) {
                    matrix[lower_bound][j] = num;
                    num++;
                }
                // lower_bound上移
                lower_bound--;
            }

            if (left_bound <= right_bound) {
                // 在左侧从下向上遍历
                for (int i = lower_bound; i >= upper_bound; i--) {
                    matrix[i][left_bound] = num;
                    num++;
                }
                // left_bound右移
                left_bound++;
            }
        }
        return matrix;
    }
}
