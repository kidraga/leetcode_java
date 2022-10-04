package leetcode.sol.P0257BinaryTreePaths;

import leetcode.sol.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * Recursively DFS and traverse the tree
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;
        String path = "";

        recursivePath(result, root, path);
        return result;
    }

    private void recursivePath(List<String> list, TreeNode node, String currPath) {
        if ((node.left == null) && (node.right == null)) {
            if (currPath.length() == 0) {
                currPath = String.valueOf(node.val);
            } else {
                currPath += ("->" + String.valueOf(node.val));
            }
            list.add(currPath);
            return;
        }

        if (currPath.length() == 0) {
            currPath = String.valueOf(node.val);
        } else {
            currPath += ("->" + String.valueOf(node.val));
        }

        if (node.left != null) recursivePath(list, node.left, currPath);
        if (node.right != null) recursivePath(list, node.right, currPath);
    }
}

