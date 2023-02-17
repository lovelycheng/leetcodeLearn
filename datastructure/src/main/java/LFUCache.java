import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author chengtong
 * @date 2023/2/12 15:14
 */
public class LFUCache {

    private int capacity;
    private int size;
    private HashMap<Integer, Node> hashMap;
    private int minFreq = 0;
    private ArrayList<LinkedList<Node>> lfuList = new ArrayList<>();

    private static class Node {
        Integer value;
        Integer key;
        int frequency = 1;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Node))
                return false;
            Node node = (Node) o;
            return Objects.equals(value, node.value) && Objects.equals(key, node.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, key);
        }
    }

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.hashMap = new HashMap<>(capacity << 1);
        LinkedList<Node> first = new LinkedList<>();
        lfuList.add(0, first);
        LinkedList<Node> second = new LinkedList<>();
        lfuList.add(1, second);
        LinkedList<Node> three = new LinkedList<>();
        lfuList.add(2, three);
        LinkedList<Node> four = new LinkedList<>();
        lfuList.add(3, four);
    }

    public int get(int key) {
        Node node = this.hashMap.get(key);
        if (node == null) {
            return -1;
        }
        if (node.frequency == 4) {
            LinkedList<Node> fourth = lfuList.get(node.frequency - 1);
            fourth.remove(node);
            fourth.addFirst(node);
        } else {
            LinkedList<Node> first = lfuList.get(node.frequency - 1);
            LinkedList<Node> moveTo = lfuList.get(node.frequency);
            if (moveTo == null) {
                moveTo = new LinkedList<>();
                lfuList.add(node.frequency, first);
            }
            first.remove(node);
            moveTo.addFirst(node);
            node.frequency++;
            LinkedList<Node> minFreList = lfuList.get(minFreq-1);
            if(minFreList.isEmpty()){
                minFreq++;
            }
        }

        return node.value;
    }

    public void put(int key, int value) {
        Node node = this.hashMap.get(key);
        if (node == null) {
            this.size++;
            if (size > capacity) {
                LinkedList<Node> first = lfuList.get(minFreq - 1);
                if (first.size() > 0) {
                    Node removeLast = first.removeLast();
                    hashMap.remove(removeLast.key, removeLast);
                    size = size - 1;
                }
                if (first.isEmpty()) {
                    minFreq++;
                }
            }
            LinkedList<Node> first = lfuList.get(0);
            Node newNode = new Node(key, value);
            first.addFirst(newNode);
            hashMap.put(key, newNode);
            minFreq = 1;
        } else {
            node.value = value;
            get(key);
        }

    }

    /**
     * ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
     * [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
     *
     * @param args
     */
    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        lfuCache.get(1);
        lfuCache.put(3, 3);
        lfuCache.get(2);
        lfuCache.get(3);
        lfuCache.put(4, 4);
        lfuCache.get(1);
        lfuCache.get(3);
        lfuCache.get(4);
    }
}
