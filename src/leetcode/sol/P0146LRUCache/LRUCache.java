package leetcode.sol.P0146LRUCache;

import java.util.HashMap;
import java.util.Map;

class Node {
    Node prev;
    Node next;
    int key;
    int value;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class LRUCache {
    int capacity;
    Node head; // head saves most recently used value
    Node tail;
    Map<Integer, Node> keyToNode = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
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
                keyToNode.remove(tail.key);
                removeNode(tail);
            }
            Node newNode = new Node(key, value);
            keyToNode.put(key, newNode);
            setHead(newNode);
        }
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        // update head and tail pointers
        if (node == head) head = head.next;
        if (node == tail) tail = tail.prev;
        // connect prev and next together
        if (prev != null) prev.next = next;
        if (next != null) next.prev = prev;
    }

    private void setHead(Node node) {
        node.next = head;
        node.prev = null;
        if (head != null) head.prev = node;
        head = node;
        // if only one node is in the list,
        // we need to set tail to head
        if (tail == null) tail = head;
    }

}
