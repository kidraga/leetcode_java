package leetcode.sol.P1011CapacityToShipPackagesWithinDDays;

public class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int total = 0;
        int maxWeight = 0;
        for (int weight : weights) {
            total += weight;
            maxWeight = Math.max(maxWeight, weight);
        }
        int left = maxWeight;
        int right = total + 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canShip(weights, mid, days)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canShip(int[] weights, int capacity, int days) {
        int totalPerDay = 0;
        int daysNeeded = 1;
        for (int weight : weights) {
            totalPerDay += weight;
            if (totalPerDay > capacity) {
                daysNeeded += 1;
                totalPerDay = weight;
            }
        }
        return daysNeeded <= days;
    }
}
