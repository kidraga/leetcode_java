package leetcode.sol.P1650LowestCommonAncestorOfABinaryTreeIII;

import leetcode.sol.util.Node;

public class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        // 链表双指针
        // 等同于两条单链表找交点
        Node a = p, b = q;
        while (a != b) {
            // a走一步，如果走到根节点，转到q节点
            if (a == null) {
                a = q;
            } else {
                a = a.parent;
            }
            // b走一步，如果走到根节点，转到p节点
            if (b == null) {
                b = p;
            } else {
                b = b.parent;
            }
        }
        return a;
    }
}
