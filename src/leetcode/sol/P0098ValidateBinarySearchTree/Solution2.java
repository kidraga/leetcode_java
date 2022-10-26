package leetcode.sol.P0098ValidateBinarySearchTree;

import leetcode.sol.util.TreeNode;

/**
 * Wrong answer.
 * 出现问题的原因在于，对于每一个节点 root，代码值检查了它的左右孩子节点是否符合左小右大的原则；
 * 但是根据 BST 的定义，root 的整个左子树都要小于 root.val，整个右子树都要大于 root.val。
 */
public class Solution2 {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (root.left != null && root.left.val >= root.val) return false;
        if (root.right != null && root.right.val <= root.val) return false;

        return isValidBST(root.left) && isValidBST(root.right);
    }
}
