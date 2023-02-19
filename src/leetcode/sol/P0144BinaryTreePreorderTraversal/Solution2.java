package leetcode.sol.P0144BinaryTreePreorderTraversal;

import java.util.ArrayList;
import java.util.List;

import leetcode.sol.util.TreeNode;

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        traverse(root, result);
        return result;
    }

    private void traverse(TreeNode node, List<Integer> result) {
        if (node == null) return;

        result.add(node.val);

        traverse(node.left, result);
        traverse(node.right, result);
    }
}
