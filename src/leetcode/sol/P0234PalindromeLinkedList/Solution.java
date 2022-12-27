package leetcode.sol.P0234PalindromeLinkedList;

import leetcode.sol.util.ListNode;

import java.util.Stack;

class Solution {
    public boolean isPalindrome(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        ListNode p1 = head;
        ListNode p2 = null;
        while (!stack.isEmpty()) {
            p2 = stack.pop();
            if (p1.val != p2.val) return false;
            p1 = p1.next;
        }
        return true;
    }
}
