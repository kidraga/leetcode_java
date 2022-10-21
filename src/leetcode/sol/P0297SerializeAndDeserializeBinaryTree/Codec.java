package leetcode.sol.P0297SerializeAndDeserializeBinaryTree;

import leetcode.sol.util.TreeNode;

import java.util.LinkedList;

public class Codec {
    private static String SEP = ",";
    private static String NULL = "#";
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }
        // preorder
        sb.append(root.val).append(SEP);
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    public TreeNode deserialize(String data) {
        // 后面要用到removeFirst，所以这里用linked list
        LinkedList<String> nodes = new LinkedList<>();
        for (String nodeVal: data.split(SEP)) {
            nodes.add(nodeVal);
        }
        return deserialize(nodes);
    }

    private TreeNode deserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }

        // preorder的第一个node就是root
        String nodeVal = nodes.removeFirst();
        if (NULL.equals(nodeVal)) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(nodeVal));
        root.left = deserialize(nodes);
        root.right = deserialize(nodes);
        return root;
    }
}
