package leetcode.sol.P0105ConstructBinaryTreeFromPreorderAndInorderTraversal;

import leetcode.sol.util.TreeNode;

public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(inorder, 0, inorder.length - 1, preorder, 0);
    }

    /**
     *
     * @param inorder The original inorder tree node array
     * @param inStart The starting index in inorder array of the subTree we are trying to build
     * @param inEnd The ending index in inorder array of the subTree we are trying to build
     * @param preorder The original preorder tree node array
     * @param preStart The index of current root in preorder array
     * @return
     */
    private TreeNode build(int[] inorder, int inStart, int inEnd, int[] preorder, int preStart) {

        // the inStart and inEnd mark the range of all nodes for current tree in inorder array.
        // if inStart > inEnd, it means there's no node in current range, which means no subtree for parent node.
        if (inStart > inEnd) {
            return null;
        }

        // In a preorder array, the root is always the first node.
        // This applies for any subtree's corresponding preorder array.
        // We need to find the starting index for the subtree in the original preorder array (the array for the full tree)
        TreeNode currRoot = new TreeNode(preorder[preStart]);
        if (inStart == inEnd) return currRoot; // this makes it faster, avoid doing extra calculation. can be removed.

        // find the index of current root in range [inStart, inEnd], which contains all nodes for current subtree
        // everything on the left of this index will be the range of left subtree, i.e [inStart, currRootIndexInInorder -1 ]
        // everything on the right will be range of right subtree, i.e [currRootIndexInInorder + 1, inEnd]
        int currRootIndexInInorder = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == currRoot.val) {
                currRootIndexInInorder = i;
                break;
            }
        }

        /*
            build left and right subtree
            all nodes needed in inorder has been calculated above
            for left subtree, all indices of nodes in inorder are [inStart, currRootIndexInInorder -1],
            we need to find where does the subtree start in preorder so we can get the root value in next level
            for current level, we know it looks like this in the original full preorder array:

            [..., [root],[left subtree nodes], [right subtree nodes], ...]

            We also know root's index is preStart.
            Apparently, left subtree's root index is preStart + 1.
            To find right subtree's root index, we need to know how many nodes are in left subtrees.
            This is already known since we got the range of left subtree in inorder, which is [inStart, currRootIndexInInorder -1].
            The length of this is (currRootIndexInInorder - 1) - inStart + 1 = currRootIndexInInorder - inStart
            Then the index of right subtree's root = preStart + (currRootIndexInInorder - inStart) + 1
         */
        currRoot.left = build(inorder, inStart, currRootIndexInInorder - 1, preorder, preStart + 1);
        currRoot.right = build(inorder, currRootIndexInInorder + 1,  inEnd, preorder, preStart + (currRootIndexInInorder - inStart) + 1);
        return currRoot;
    }
}
