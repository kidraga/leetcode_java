package leetcode.sol.P0103BinaryTreeZigzagLevelOrderTraversal;

import leetcode.sol.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        dfs(root, 0, result);
        return result;
    }

    private void dfs(TreeNode root, int level, List<List<Integer>> result) {
        if (root == null) return;

        // when first enter a level, the size of the result list is equal to level.
        if (result.size() == level) result.add(new ArrayList<Integer>());

        if (level % 2 == 0) {
            result.get(level).add(root.val);
        } else {
            result.get(level).add(0, root.val);
        }

        dfs(root.left, level + 1, result);
        dfs(root.right, level + 1, result);
    }
}
