import java.util.HashMap;
import java.util.Map;

/**
 * @author chengtong
 * @date 2022/12/13 11:45
 */
public class LRUCache {

    private static class Node {
        private Node prev;
        private Node next;
        private Integer key;
        private Integer value;

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

    private Map<Integer, Node> data = new HashMap<Integer, Node>();

    private final int capacity;

    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    private void putToHead(Node node) {
        if (this.head.equals(node)) {
            return;
        }

        if (this.tail.equals(node) && node.prev != null) {
            this.tail = node.prev;
        }

        // geted.prev.next = geted.next
        node.getPrev()
            .setNext(node.getNext());
        // geted.next.prev = geted.prev
        if (node.getNext() != null) {
            node.getNext()
                .setPrev(node.getPrev());
        }

        Node headNode = this.head;
        headNode.prev = node;
        node.next = headNode;
        this.head = node;
    }

    public int get(int key) {
        if (!data
            .containsKey(key)) {
            return -1;
        }
        Node geted = data.get(key);
        putToHead(geted);
        return data.get(key)
            .getValue();
    }

    public void put(int key, int value) {

        if(get(key) != -1){
            this.data.get(key).setValue(value);
            return;
        }

        Node node = new Node();
        node.key = key;
        node.value = value;
        this.data.put(key, node);
        if (data.size() == 1) {
            this.tail = node;
            this.head = node;
            return;
        }

        Node headNode = this.head;
        headNode.prev = node;
        node.next = headNode;
        this.head = node;

        if (data.size() > capacity) {
            Node nodeTail = this.tail;
            data.remove(nodeTail.key);
            nodeTail.prev.next = null;
            this.tail = nodeTail.prev;
        }
    }

    /**
     * ["LRUCache","put","put","put","put","get","get"]
     * [[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
     * @param args
     */
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2,1);
        lruCache.put(1,1);
        lruCache.put(2,3);
        lruCache.put(4,1);
        lruCache.get(1);
        lruCache.get(2);
    }

}
