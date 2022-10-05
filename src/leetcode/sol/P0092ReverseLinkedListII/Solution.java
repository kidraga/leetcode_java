package leetcode.sol.P0092ReverseLinkedListII;

import leetcode.sol.util.ListNode;

/**
 * https://labuladong.github.io/algo/2/19/19/
 */
public class Solution {

    private ListNode successor = null;

    public ListNode reverseBetween(ListNode head, int left, int right) {
        // base case
        if (left == 1) {
            return reverseN(head, right);
        }
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    /**
     * 反转以head为起点的n个node，并返回新的头的node
     */
    private ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        // 以head.next为起点，需要反转前n-1个node
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        head.next = successor;
        return last;
    }
}
