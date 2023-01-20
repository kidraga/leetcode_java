package leetcode.sol.P0145BinaryTreePostorderTraversal;

import java.util.ArrayList;
import java.util.List;

import leetcode.sol.util.TreeNode;

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        dfs(root, result);
        return result;
    }

    private void dfs(TreeNode node, List<Integer> result) {
        if (node == null) return;

        dfs(node.left, result);
        dfs(node.right, result);
        result.add(node.val);
    }
}
