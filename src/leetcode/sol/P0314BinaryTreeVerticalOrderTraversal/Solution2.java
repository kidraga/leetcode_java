package leetcode.sol.P0314BinaryTreeVerticalOrderTraversal;

import javafx.util.Pair;
import leetcode.sol.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution2 {

    /**
     * Keep updating the smallest and largest column number, so we know the range
     * In the end, we don't need to sort column number to construct the result.
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        /*
        create a map that keep each column.
        key is the column number. The root of the whole tree has column 0, to left is -1, -2, to right +1,+2
        {
        -2 : [4],
        -1: [9],
        0: [3, 0, 1],
        1: [8],
        2: [7]
        }
         */
        Map<Integer, List> columnToVerticalList = new HashMap<>();
        Deque<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        int column = 0;
        queue.offer(new Pair(root, column));
        int minColumn = 0, maxColumn = 0;

        while (!queue.isEmpty()) {

            for (int i = queue.size(); i > 0; i--) {
                Pair<TreeNode, Integer> p = queue.poll();
                TreeNode currRoot = p.getKey();
                int columnOfRoot = p.getValue();

                if (currRoot != null) {
                    if (!columnToVerticalList.containsKey(columnOfRoot)) {
                        columnToVerticalList.put(columnOfRoot, new ArrayList<Integer>());
                    }
                    columnToVerticalList.get(columnOfRoot).add(currRoot.val);
                    minColumn = Math.min(minColumn, columnOfRoot);
                    maxColumn = Math.max(maxColumn, columnOfRoot);

                    if (currRoot.left != null) {
                        queue.offer(new Pair(currRoot.left, columnOfRoot - 1));
                    }
                    if (currRoot.right != null) {
                        queue.offer(new Pair(currRoot.right, columnOfRoot + 1));
                    }
                }
            }
        }

        for (int i = minColumn; i < maxColumn + 1; i++) {
            result.add(columnToVerticalList.get(i));
        }
        return result;
    }
}
