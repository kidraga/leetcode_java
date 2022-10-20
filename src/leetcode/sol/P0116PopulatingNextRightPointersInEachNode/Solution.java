package leetcode.sol.P0116PopulatingNextRightPointersInEachNode;

import leetcode.sol.util.Node;

/**
 * https://labuladong.github.io/algo/2/21/37/
 */
public class Solution {
    public Node connect(Node root) {
        if (root == null) return null;
        // 遍历“三叉树”，链接相邻节点
        traverse(root.left, root.right);
        return root;
    }

    private void traverse(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        /* 前序位置 */
        // 将传入的两个节点穿起来
        node1.next = node2;

        // 连接相同父节点的两个子节点
        traverse(node1.left, node1.right);
        traverse(node2.left, node2.right);
        // 连接跨越父节点的两个子节点
        traverse(node1.right, node2.left);
    }
}
