package leetcode.sol.P0025ReverseNodesInKGroup;

import leetcode.sol.util.ListNode;

class Solution{
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        // [a, b) has k nodes that pending reverse
        ListNode a = head;
        ListNode b = head;
        for (int i = 0; i < k; i++) {
            // move b to kth node
            if (b == null) return head; // if there's less than k's nodes in list, no need to reverse
            b = b.next;
        }

        // reverse the first k's nodes
        ListNode newHead = reverseAToB(a, b);
        // get the rest of the list reversed by recursion
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    private ListNode reverseAToB(ListNode a, ListNode b) {
        ListNode prev = null;
        ListNode curr = a;
        ListNode next = a;
        while (curr != b) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
