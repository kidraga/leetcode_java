package leetcode.sol.P0155MinStack;

import java.util.Stack;

class MinStack2 {

    private Stack<int[]> stack;

    public MinStack2() {
        stack = new Stack<>();
    }

    // O(1)
    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(new int[]{val, val});
            return;
        }
        int currMin = stack.peek()[1];
        stack.push(new int[]{val, Math.min(currMin, val)});
    }

    // O(1)
    public void pop() {
        stack.pop();
    }

    // O(1)
    public int top() {
        return stack.peek()[0];
    }

    // O(1)
    public int getMin() {
        return stack.peek()[1];
    }
}
