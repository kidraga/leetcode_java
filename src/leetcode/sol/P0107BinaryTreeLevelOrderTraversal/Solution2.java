package leetcode.sol.P0107BinaryTreeLevelOrderTraversal;

import leetcode.sol.util.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution2 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();

        if (root == null) return levels;

        // Copied from Problem 102
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> listForCurrentLevel = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
               TreeNode node = queue.poll();
                listForCurrentLevel.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            levels.add(listForCurrentLevel);
        }

        // only difference with P102
        Collections.reverse(levels);

        return levels;
    }
}
