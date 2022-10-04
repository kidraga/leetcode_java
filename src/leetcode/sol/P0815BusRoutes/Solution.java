package leetcode.sol.P0815BusRoutes;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    /**
     * Works but too slow
     */
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;
        if (routes == null || routes.length == 0 || routes[0].length == 0) return -1;

        // construct graph
        Map<Integer, Set<Integer>> routesGraph = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            Set<Integer> stopsInRoute = new HashSet<>();
            for (int j = 0; j < routes[i].length; j++) {
                int currStop = routes[i][j];
                stopsInRoute.add(currStop);
            }
            for (int j = 0; j < routes[i].length; j++) {
                int currStop = routes[i][j];
                if (!routesGraph.containsKey(currStop)) {
                    routesGraph.put(currStop, new HashSet<Integer>());
                }
                routesGraph.get(currStop).addAll(stopsInRoute); // {1: {1,2,7}}
            }
        }

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(source);
        int numberOfBusToTake = 0;
        Set<Integer> visitiedStops = new HashSet<>();
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            numberOfBusToTake++;
            for (int i = levelSize; i > 0; i--) {
                int currStop = queue.poll();
                visitiedStops.add(currStop);
                // if (currStop == target) return numberOfBusToTake;
                Set<Integer> nextStops = routesGraph.get(currStop);
                if (nextStops.contains(target)) return numberOfBusToTake;
                for (int stop : nextStops) {
                    if (!visitiedStops.contains(stop)) {
                        queue.offer(stop);
                    }
                }
            }
        }
        return -1;
    }
}
