package leetcode.sol.P2374NodeWithHighestEdgeScore;

class Solution {
    public int edgeScore(int[] edges) {
        // Score can go overflow, so use long[]
        long[] nodeScore = new long[edges.length];

        for (int i = 0; i < edges.length; i++) {
            int toNode = edges[i];
            nodeScore[toNode] += i;
        }

        long maxScore = 0;
        int nodeWithMaxScore = 0;
        for (int i = 0; i < nodeScore.length; i++) {
            if (nodeScore[i] > maxScore) {
                maxScore = Math.max(maxScore, nodeScore[i]);
                nodeWithMaxScore = i;
            }
        }
        return nodeWithMaxScore;
    }
}

