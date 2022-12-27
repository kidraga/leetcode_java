package leetcode.sol.P0222CountCompleteTreeNodes;

import leetcode.sol.util.TreeNode;

/**
 * https://labuladong.github.io/algo/2/21/48/
 * Time: O(logN*logN)
 */
class Solution {
    public int countNodes(TreeNode root) {
        TreeNode left = root, right = root;
        // 沿最左侧和最右侧分别计算高度
        int heightLeft = 0, heightRight = 0;
        while (left != null) {
            left = left.left;
            heightLeft++;
        }
        while (right != null) {
            right = right.right;
            heightRight++;
        }
        // 如果左右侧高度相同，则是一棵满二叉树
        // 节点数 = 2^(height) - 1
        if (heightLeft == heightRight) {
            return (int) Math.pow(2, heightLeft) - 1;
        }
        // 如果左右侧的高度不同，则按照普通二叉树的逻辑计算
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
