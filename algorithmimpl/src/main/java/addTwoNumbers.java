/**
 * @author chengtong
 * @date 2023/2/9 16:49
 */
public class addTwoNumbers {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {this.val = val;}

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" + "val=" + val + ", next=" + next + '}';
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        System.err.println(addTwoNumbers(l1, l2));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int t = 0;

        ListNode a = new ListNode(0);
        ListNode at = a;

        while (l1 != null || l2 != null) {
            int v1 = (l1 == null ? 0 : l1.val);
            int v2 = (l2 == null ? 0 : l2.val);
            at.next = new ListNode((v1 + v2 + t) % 10);
            t = (v1 + v2 + t) / 10;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            if (l1 == null && l2 == null) {
                break;
            }
            at = at.next;
        }

        return a.next;
    }

}
