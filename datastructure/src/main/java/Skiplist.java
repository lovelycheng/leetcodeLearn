import java.util.Arrays;
import java.util.Random;

/**
 * @author chengtong
 * @date 2023/2/18 13:17
 */
public class Skiplist {

    class Node {
        int value;
        Node[] forward;

        public Node(int value, int maxLevel) {
            this.value = value;
            this.forward = new Node[maxLevel];
        }
    }

    private int maxLevel = 1;
    private int currLevel = 0;
    private Node head;
    private float rate = 0.25f;
    private Random random;
    public Skiplist() {
        this.head = new Node(-1, maxLevel);
        this.random = new Random();
    }

    public boolean search(int target) {
        Node node = head;
        for (int i = currLevel - 1; i >= 0; i--) {
            while (node.forward[i] != null && node.forward[i].value < target) {
                node = node.forward[i];
            }
        }
        node = node.forward[0];
        return node != null && node.value == target;
    }

    private int getLevel() {
        double d = Math.random();
        int i = 1;
        while (d < rate && i < maxLevel - 1) {
            i++;
            d = random.nextDouble();
        }
        return i;
    }

    public void add(int num) {
        Node node = head;
        //记录查询经过的所有节点 节点的下一个指针需要修改 先将整个路径布满head
        Node[] updates = new Node[maxLevel];
        Arrays.fill(updates, head);
        for (int i = currLevel - 1; i >= 0; i--) {
            while (node.forward[i] != null && node.forward[i].value < num) {
                node = node.forward[i];
            }
            updates[i] = node;
        }
        // if (node.forward[0] != null && node.forward[0].value == num) {
        //     return;
        // }
        Node newNode = new Node(num, maxLevel);

        //linked list op
        int d = getLevel();
        int realLevel = Math.max(d, currLevel);
        currLevel = realLevel;
        for (int j = 0; j < realLevel; j++) {
            newNode.forward[j] = updates[j].forward[j];
            updates[j].forward[j] = newNode;
        }
    }

    public boolean erase(int num) {
        Node node = head;
        //记录查询经过的所有节点 节点的下一个指针需要修改 先将整个路径布满head
        Node[] updates = new Node[maxLevel];
        Arrays.fill(updates, head);
        for (int i = currLevel - 1; i >= 0; i--) {
            while (node.forward[i] != null && node.forward[i].value < num) {
                node = node.forward[i];
            }
            updates[i] = node;
        }
        node = node.forward[0];
        if (node == null || node.value != num) {
            return false;
        }
        for (int i = 0; i < currLevel; i++) {
            if (updates[i].forward[i] != node) {
                continue;
            }
            updates[i].forward[i] = node.forward[i];
        }
        for (int i = 0; i < currLevel; i++) {
            if (head.forward[i] == null) {
                this.currLevel--;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();
        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        System.out.println(skiplist.search(0));
        skiplist.add(4);
        skiplist.add(5);
        System.out.println(skiplist.search(1));
        System.out.println(skiplist.erase(0));
        System.out.println(skiplist.erase(1));
        System.out.println(skiplist.search(1));
        System.out.println(skiplist.search(3));
        System.out.println(skiplist.search(2));
    }
}
