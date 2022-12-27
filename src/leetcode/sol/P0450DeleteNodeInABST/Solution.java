package leetcode.sol.P0450DeleteNodeInABST;

import leetcode.sol.util.TreeNode;

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            // found. Delete the node
            // case A: this node is a leaf
            if (root.left == null && root.right == null) {
                return null;
            }
            // case B: only one child. Connect directly
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // case C: two children.
            // 必须找到左子树中的最大节点，或者右子树的最小节点，来接替root
            if (root.left != null && root.right != null) {
                // 找到右子树的最小节点
                TreeNode minNode = getMin(root.right);
                // 删除右子树的最小节点
                root.right = deleteNode(root.right, minNode.val);
                // 用右子树最小节点替换root节点
                minNode.left = root.left;
                minNode.right = root.right;
                root = minNode;
            }
        } else if (root.val > key) {
            // go to left child
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            // go to right child
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    private TreeNode getMin(TreeNode node) {
        // BST最左边的就是最小的
        while (node.left != null) node = node.left;
        return node;
    }
}
