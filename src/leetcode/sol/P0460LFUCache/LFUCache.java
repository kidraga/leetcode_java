package leetcode.sol.P0460LFUCache;

import java.util.HashMap;
import java.util.Map;

class LFUCache {

    class Node {
        int key;
        int value;
        int count; // frequency. 这个值被用了几次
        Node prev;
        Node next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
            count = 1;
            prev = null;
            next = null;
        }
    }

    class DLList { // double linked list.
        Node head;
        Node tail;
        int size; // list size.
        DLList() {
            // initialized two dummy nodes as head and tail
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        /**
         * Add node to the one after head
         */
        public void add(Node node) {
            head.next.prev = node;
            node.next = head.next;
            node.prev = head;
            head.next = node;
            size++;
        }

        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeLast() {
            if (size > 0) {
                Node node = tail.prev;
                remove(node);
                return node;
            }
            return null;
        }
    }

    private int capacity;
    private int size;
    private int min;
    private Map<Integer, Node> keyToNodeMap;
    // 同一count可能有多个值，需要存到一个list里
    private Map<Integer, DLList> countToListMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyToNodeMap = new HashMap<>();
        countToListMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = keyToNodeMap.getOrDefault(key, null);
        if (node == null) return -1; // key不存在
        update(node); // 刚刚使用了node，更新node里的count，并需要对应更新所在的list
        return node.value;
    }

    public void put(int key, int value) {
        // Nothing in cache.
        if (capacity == 0) return;
        Node node;
        // key is in the cache,
        // then update the value, and update usage: put node to the first node
        if (keyToNodeMap.containsKey(key)) {
            node = keyToNodeMap.get(key);
            node.value = value;
            update(node);
        } else {
            // key doesn't exist yet.
            // create new node,
            // update usage
            node = new Node(key, value);
            keyToNodeMap.put(key, node);
            // check if we are reaching capacity
            if (size == capacity) {
                // if we reached capacity,
                // remove the last node: the least used node
                DLList lastlist = countToListMap.get(min);
                keyToNodeMap.remove(lastlist.removeLast().key);
                size--;
            }
            size++;
            min = 1;
            DLList newList = countToListMap.getOrDefault(node.count, new DLList());
            newList.add(node);
            countToListMap.put(node.count, newList);
        }
    }

    /**
     * Update node to be after head,
     * since we just used this node.
     */
    private void update(Node node) {
        DLList oldList = countToListMap.get(node.count);
        oldList.remove(node);
        if (node.count == min && oldList.size == 0) min++;
        node.count++;
        DLList newList = countToListMap.getOrDefault(node.count, new DLList());
        newList.add(node);
        countToListMap.put(node.count, newList);
    }
}
