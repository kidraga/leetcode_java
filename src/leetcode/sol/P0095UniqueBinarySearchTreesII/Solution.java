package leetcode.sol.P0095UniqueBinarySearchTreesII;

import leetcode.sol.util.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class Solution {

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<TreeNode>();

        return createTreesFromRange(1, n);

    }

    private List<TreeNode> createTreesFromRange(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if (start > end) {
            // add empty tree
            allTrees.add(null);
            return allTrees;
        }

        // pick a root
        for (int i = start; i <= end; i++) {
            // create all subtrees using indices on the left of current index (i.e. number smaller than index).
            List<TreeNode> leftTrees = createTreesFromRange(start, i - 1);
            // create all subtrees using indices on the right of current index (i.e. number larger than index).
            List<TreeNode> rightTrees = createTreesFromRange(i + 1, end);

            // connect left and right trees
            for (TreeNode leftSubTreeRoot : leftTrees) {
                for (TreeNode rightSubTreeRoot : rightTrees) {
                    TreeNode currentRoot = new TreeNode(i);
                    currentRoot.left = leftSubTreeRoot;
                    currentRoot.right = rightSubTreeRoot;
                    allTrees.add(currentRoot);
                }
            }
        }
        return allTrees;
    }
}
