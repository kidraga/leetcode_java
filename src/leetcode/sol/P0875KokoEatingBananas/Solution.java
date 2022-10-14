package leetcode.sol.P0875KokoEatingBananas;

public class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = Integer.MIN_VALUE;

        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long hours = getTotalHours(piles, mid);
            if (hours > h) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private long getTotalHours(int[] piles, int k) {
        long hours = 0;
        for (int pile : piles) {
            hours += pile % k == 0 ? pile / k : pile / k + 1; // hours may overflow, so has to be a long
        }
        return hours;
    }
}
