package leetcode.sol.P0048RotateImage;

public class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 先沿左下右上对角线镜像对称二维矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // swap(matrix[i][j], matrix[j][i]);
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 然后反转二维矩阵的每一行
        for (int[] row : matrix) {
            reverse(row);
        }
    }

    private void reverse(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        while (j > i) {
            // swap(arr[i], arr[j])
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}
