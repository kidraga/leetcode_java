package leetcode.sol.P2187MinimumTimeToCompleteTrips;

class Solution2 {

    public long minimumTime(int[] time, int totalTrips) {
        int minTime = Integer.MAX_VALUE;
        for (int t : time) {
            minTime = Math.min(minTime, t);
        }

        // binary search to find left boundary, which is minimum time needed
        long left = 0;
        // long right = minTime * totalTrips; technically this is correct
        long right = 100_000_000_000_000L; // this is only correct because we know there's an answer in long
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long trips = calculateTrips(mid, time);
            // we need to find left boundary
            if (trips >= totalTrips) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private long calculateTrips(long givenTime, int[] time) {
        int canFinish = 0;
        for (int t : time) {
            canFinish += givenTime / t;
        }
        return canFinish;
    }
}
