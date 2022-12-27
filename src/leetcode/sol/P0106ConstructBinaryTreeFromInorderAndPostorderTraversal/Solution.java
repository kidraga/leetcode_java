package leetcode.sol.P0106ConstructBinaryTreeFromInorderAndPostorderTraversal;

import leetcode.sol.util.TreeNode;

/**
 * Same as Problem 105
 */
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, inorder.length - 1, 0, postorder, postorder.length - 1);
    }

    private TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart) {
        if (inEnd > inStart) {
            return null;
        }

        TreeNode currRoot = new TreeNode(postorder[postStart]);
        if (inEnd == inStart) return currRoot;

        int currRootIndexInInorder = 0;
        for (int i = inStart; i >= inEnd; i--) {
            if (inorder[i] == currRoot.val) {
                currRootIndexInInorder = i;
                break;
            }
        }

        currRoot.right = build(inorder, inStart, currRootIndexInInorder + 1, postorder, postStart - 1);
        currRoot.left = build(inorder, currRootIndexInInorder - 1, inEnd, postorder, postStart - (inStart - currRootIndexInInorder) - 1);
        return currRoot;
    }
}
