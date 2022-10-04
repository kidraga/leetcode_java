import leetcode.sol.P0257BinaryTreePaths.Solution;
import leetcode.sol.util.TreeNode;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TreeNode testTree = new TreeNode(1);
        testTree.left = new TreeNode(2);
        testTree.right = new TreeNode(3);
        testTree.left.right = new TreeNode(5);

        Solution sol = new Solution();
        List<String> result = sol.binaryTreePaths(testTree);
        System.out.println("Result: " + result);
    }
}
