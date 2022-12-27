package leetcode.sol.P0102BinaryTreeLevelOrderTraversal;

import leetcode.sol.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Solution {

    /**
     * Recursive solution
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        dfs(result, root, 0);
        return result;
    }

    private void dfs(List<List<Integer>> result, TreeNode node, int level) {

        if (node == null) return;
        // level equals result.size() when we reach this level for the first time.
        if (level == result.size()) result.add(new ArrayList<Integer>());

        result.get(level).add(node.val);
        dfs(result, node.left, level + 1);
        dfs(result, node.right, level + 1);
    }
}
