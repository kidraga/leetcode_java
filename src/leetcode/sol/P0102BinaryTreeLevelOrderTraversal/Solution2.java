package leetcode.sol.P0102BinaryTreeLevelOrderTraversal;

import leetcode.sol.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution2 {

    public List<List<Integer>> LevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        // use a queue to save all nodes in a level
        // every time we enter a level, we take out all nodes in the queue,
        // save values into results, then put all child nodes into the queue for next loop
        // loop ends when there's no child nodes.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {

            List<Integer> listForCurrentLevel = new ArrayList<>();
            // cannot use (int i = 0; i < queue.size(); i++),
            // because queue.size() is changing in the loop.
            // we push child node to end of the queue,
            // and take node out from the start of the queue.
            // This requires the queue to be FIFO.
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                listForCurrentLevel.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            result.add(listForCurrentLevel);
        }

        return result;
    }
}
