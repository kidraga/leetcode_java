package leetcode.sol.P0234PalindromeLinkedList;

import leetcode.sol.util.ListNode;

class Solution2 {
    private ListNode left;
    public boolean isPalindrome(ListNode head) {
        left = head;
        return traverse(head);
    }

    private boolean traverse(ListNode right) {
        if (right == null) return true;
        boolean res = traverse(right.next);
        res = res && (right.val == left.val);
        left = left.next;
        return res;
    }
}
