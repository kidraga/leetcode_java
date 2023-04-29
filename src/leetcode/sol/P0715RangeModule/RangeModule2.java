package leetcode.sol.P0715RangeModule;

class SegmentTreeNode {
    int start;
    int end;
    boolean tracked;
    SegmentTreeNode leftChild;
    SegmentTreeNode rightChild;

    public SegmentTreeNode(int start, int end, boolean tracked) {
        this.start = start;
        this.end = end;
        leftChild = null;
        rightChild = null;
        this.tracked = tracked;
    }
}

class SegmentTree {

    private SegmentTreeNode root;

    public SegmentTree(int start, int end) {
        root = new SegmentTreeNode(start, end, false);
    }

    public void modify(int start, int end, boolean track) {
        modify(root, start, end, track);
    }

    private void modify(SegmentTreeNode node, int start, int end, boolean track) {
        if (node.start == start && node.end == end) {
            node.tracked = track;
            node.leftChild = null;
            node.rightChild = null;
            return;
        }

        int mid = node.start + (node.end - node.start) / 2;

        if (node.leftChild == null) {
            node.leftChild = new SegmentTreeNode(node.start, mid, node.tracked);
            node.rightChild = new SegmentTreeNode(mid + 1, node.end, node.tracked);
        }

        if (start <= mid) {
            modify(node.leftChild, start, Math.min(end, mid), track);
        }

        if (end >= mid + 1) {
            modify(node.rightChild, Math.max(start, mid + 1), end, track);
        }

        node.tracked = node.leftChild.tracked & node.rightChild.tracked;
        // same as node.tracked = (node.left.tracked && node.right.tracked) ? true : false;
    }

    public boolean query(int start, int end) {
        return query(root, start, end);
    }

    private boolean query(SegmentTreeNode node, int start, int end) {
        // not splits in between
        if (node.leftChild == null) return node.tracked;

        if (node.start == start && node.end == end) {
            return node.tracked;
        }

        int mid = node.start + (node.end - node.start) / 2;

        boolean leftTracked = true;
        boolean rightTracked = true;

        if (start <= mid) {
            leftTracked = query(node.leftChild, start, Math.min(end, mid));
        }

        if (end >= mid + 1) {
            rightTracked = query(node.rightChild, Math.max(mid + 1, start), end);
        }

        return leftTracked & rightTracked;
    }

}

class RangeModule2 {

    private SegmentTree tree;

    public RangeModule2() {
        tree = new SegmentTree(1, (int) 1e9);
    }

    public void addRange(int left, int right) {
        tree.modify(left, right - 1, true);
    }

    public boolean queryRange(int left, int right) {
        return tree.query(left, right - 1);
    }

    public void removeRange(int left, int right) {
        tree.modify(left, right - 1, false);
    }
}
