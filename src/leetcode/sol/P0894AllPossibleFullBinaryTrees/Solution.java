package leetcode.sol.P0894AllPossibleFullBinaryTrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import leetcode.sol.util.TreeNode;

public class Solution {
    private Map<Integer, List<TreeNode>> cache = new HashMap<>();
    public List<TreeNode> allPossibleFBT(int n) {
        if (n % 2 == 0) return new ArrayList<>();
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        TreeNode oneNodeTree = new TreeNode(0);
        List<TreeNode> treeList = new ArrayList<>();
        if (n == 1) {
            treeList.add(oneNodeTree);
            return treeList;
        }

        for (int numNodesOnLeft = 1; numNodesOnLeft < n; numNodesOnLeft+=2) {
            int numNodesOnRight = n - 1 - numNodesOnLeft;
            List<TreeNode> allPossibleLeftTrees = allPossibleFBT(numNodesOnLeft);
            List<TreeNode> allPossibleRightTrees = allPossibleFBT(numNodesOnRight);
            for (TreeNode leftChild : allPossibleLeftTrees) {
                for (TreeNode rightChild : allPossibleRightTrees) {
                    TreeNode root = new TreeNode(0);
                    root.left = leftChild;
                    root.right = rightChild;
                    treeList.add(root);
                }
            }
        }
        cache.put(n, treeList);
        return treeList;
    }
}
