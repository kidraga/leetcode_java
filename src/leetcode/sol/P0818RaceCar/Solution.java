package leetcode.sol.P0818RaceCar;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {
    public int racecar(int target) {
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(1, 0)); // speed, position
        int distance = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Pair<Integer, Integer> cur = queue.poll();
                if (cur.getValue() == target) {
                    return distance;
                }

                // if "A"
                int nextPosition = cur.getValue() + cur.getKey();
                int nextSpeed = cur.getKey() * 2;
                if (!visited.contains(cur) && Math.abs(target - nextPosition) < target) {
                    visited.add(cur);
                    queue.offer(new Pair<>(nextSpeed, nextPosition));
                }

                // if "R"
                nextPosition = cur.getValue();
                nextSpeed = cur.getKey() > 0 ? -1 : 1;
                if (!visited.contains(cur) && Math.abs(target - nextPosition) < target) {
                    visited.add(cur);
                    queue.offer(new Pair<>(nextSpeed, nextPosition));
                }
            }
            distance++;
        }
        return -1;
    }
}
