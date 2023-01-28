package leetcode.sol.P0155MinStack;

import java.util.Stack;

class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> minValues;
    private int currMin;

    public MinStack() {
        stack = new Stack<>();
        minValues = new Stack<>();
        currMin = Integer.MAX_VALUE;
    }

    // O(1)
    public void push(int val) {
        stack.push(val);
        currMin = Math.min(currMin, val);
        minValues.push(currMin);
    }

    // O(1)
    public void pop() {
        stack.pop();
        minValues.pop();
        currMin = minValues.size() == 0 ? Integer.MAX_VALUE : minValues.peek();
    }

    // O(1)
    public int top() {
        return stack.peek();
    }

    // O(1)
    public int getMin() {
        return currMin;
    }
}
