package leetcode.sol.P0104MaximumDepthOfBinaryTree;

import leetcode.sol.util.TreeNode;

class Solution {
    
    public int maxDepth(TreeNode root) {
        // DSF
//        if (root == null) return 0;
//        int left = maxDepth(root.left);
//        int right = maxDepth(root.right);
//        return Math.max(left, right) + 1;

        // Same as above
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
