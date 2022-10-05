package leetcode.sol.P0206ReverseLinkedList;

import leetcode.sol.util.ListNode;

public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode node = head;
        ListNode next = node.next;
        node.next = null;
        ListNode newHead = reverseList(next);
        if (newHead != null) {
            ListNode p = newHead;
            while (p != null && p.next != null) p = p.next;
            p.next = node;
            return newHead;
        } else {
            return node;
        }
    }
}
