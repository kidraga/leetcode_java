package leetcode.sol.P0236LowestCommonAncestorOfABinaryTree;

import leetcode.sol.util.TreeNode;

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return find(root, p.val, q.val);
    }

    private TreeNode find(TreeNode root, int val1, int val2) {
        if (root == null) {
            return null;
        }

        // 前序位置
        if (root.val == val1 || root.val == val2) {
            return root;
        }

        TreeNode left = find(root.left, val1, val2);
        TreeNode right = find(root.right, val1, val2);

        // 后序位置，已经知道左右子树是否存在目标值
        if (left != null && right != null) {
            // 当前节点是LCA节点
            return root;
        }

        return left != null ? left : right;
    }
}
