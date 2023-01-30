package leetcode.sol.P0146LRUCache;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    class Node {
        Node prev;
        Node next;
        int key;
        int value;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private Node dummyHead = new Node(-1, -1); // dummyHead.next is real head, and most recent used node
    private Node dummyTail = new Node(-1, -1); // dummyTail.prev is real tail, and least recent used node

    Map<Integer, Node> keyToNode = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public int get(int key) {
        // key doesn't exist
        if (!keyToNode.containsKey(key)) return -1;

        // key in map
        // move the node to the head of the double linked list,
        // then return the value
        Node selected = keyToNode.get(key);
        removeNode(selected);
        setHead(selected);
        return selected.value;
    }

    public void put(int key, int value) {
        // if map contains key, update the value,
        // then set node to head
        if (keyToNode.containsKey(key)) {
            Node selected = keyToNode.get(key);
            selected.value = value;
            removeNode(selected);
            setHead(selected);
        } else {
            // new key to put
            // if capacity is reached,
            // remove the least recently used node
            if (keyToNode.size() >= capacity) {
                keyToNode.remove(dummyTail.prev.key);
                removeNode(dummyTail.prev);
            }
            Node newNode = new Node(key, value);
            keyToNode.put(key, newNode);
            setHead(newNode);
        }
    }

    private void removeNode(Node node) {
        if (node == dummyHead || node == dummyTail) return; // this should never happen. Maybe assertion is better
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void setHead(Node node) {
        node.next = dummyHead.next;
        dummyHead.next.prev = node;
        dummyHead.next = node;
        node.prev = dummyHead;
    }

}
