package leetcode.sol.P0107BinaryTreeLevelOrderTraversal;

import leetcode.sol.util.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) return levels;

        dfs(levels, root, 0);

        // this is the only difference than Problem 102
        // another approach is to construct the result `levels` reversely during dfs,
        // but not really much different.
        Collections.reverse(levels);

        return levels;
    }

    private void dfs(List<List<Integer>> levels, TreeNode root, int currLevel) {
        // if we are entering a level for the first time, level.size() == currLevel,
        // then we need to create an array for that level to store the values.
        if (levels.size() == currLevel) {
            levels.add(new ArrayList<Integer>());
        }

        // After checking "first entry of current level", we are sure that
        // we have the container array to store the root value,
        // and the array must have index as currLevel in levels
        levels.get(currLevel).add(root.val);

        // process left and right child
        if (root.left != null) {
            dfs(levels, root.left, currLevel + 1);
        }
        if (root.right != null) {
            dfs(levels, root.right, currLevel + 1);
        }
    }

}
