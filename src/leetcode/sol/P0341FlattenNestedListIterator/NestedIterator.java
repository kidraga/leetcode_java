package leetcode.sol.P0341FlattenNestedListIterator;

import leetcode.sol.util.NestedInteger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 缺点：constructor计算量大。next()和hasNext()不是lazy的。
 */
public class NestedIterator implements Iterator<Integer> {

    private Iterator<Integer> it;

    public NestedIterator(List<NestedInteger> nestedList) {
        // 存放将nestedList打平的结果
        List<Integer> result = new LinkedList<>();
        for (NestedInteger node : nestedList) {
            // 以每个节点为根遍历
            traverse(node, result);
        }
        this.it = result.iterator();
    }

    //遍历以root为根的多叉树，将叶节点的值加入result列表
    private void traverse(NestedInteger root, List<Integer> result) {
        if (root.isInteger()) {
            // 到达叶节点
            result.add(root.getInteger());
            return;
        }
        // 多叉树遍历框架
        for (NestedInteger child : root.getList()) {
            traverse(child, result);
        }
    }

    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }
}
