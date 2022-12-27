package leetcode.sol.P0314BinaryTreeVerticalOrderTraversal;

import javafx.util.Pair;
import leetcode.sol.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
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

                    if (currRoot.left != null) {
                        queue.offer(new Pair(currRoot.left, columnOfRoot - 1));
                    }
                    if (currRoot.right != null) {
                        queue.offer(new Pair(currRoot.right, columnOfRoot + 1));
                    }
                }
            }
        }

        // sort the key so that we can build result from smallest column number
        // This takes O(nlogn) in worst case, where each node takes a column
        // We don't have to do this, if we know the range of column. See solution 2
        List<Integer> sortedKeys = new ArrayList<>(columnToVerticalList.keySet());
        Collections.sort(sortedKeys);
        for (int k : sortedKeys) {
            result.add(columnToVerticalList.get(k));
        }
        return result;
    }
}
