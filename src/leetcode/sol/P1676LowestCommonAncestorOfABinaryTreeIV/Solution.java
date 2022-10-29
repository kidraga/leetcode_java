package leetcode.sol.P1676LowestCommonAncestorOfABinaryTreeIV;

import leetcode.sol.util.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<Integer> values = new HashSet<>();
        for (TreeNode node : nodes) {
            values.add(node.val);
        }
        return find(root, values);
    }

    private TreeNode find(TreeNode root, Set<Integer> values) {
        if (root == null) {
            return null;
        }

        // 前序位置
        if (values.contains(root.val)) {
            return root;
        }

        TreeNode left = find(root.left, values);
        TreeNode right = find(root.right, values);
        // 后序位置，已经知道左右子树是否存在目标值
        if (left != null && right != null) {
            // 当前是LCA节点
            return root;
        }

        return left != null ? left : right;
    }
}
