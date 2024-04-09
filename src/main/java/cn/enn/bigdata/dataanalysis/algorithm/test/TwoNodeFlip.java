package cn.enn.bigdata.dataanalysis.algorithm.test;

public class TwoNodeFlip {
    static class ListNode{

        public ListNode(){

        }
        private Integer value;

        private ListNode next;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

    /**
     * 递归方式，可以隐式记录上一次的节点，以及给当前的节点赋值下一个节点。
     * @param head
     * @return
     */
    public ListNode flipTwoNodeRecursion(ListNode head){
        if(head == null){
            return head;
        }

        if(head.getNext() == null){
            return head;
        }

        ListNode first = head.getNext();
        ListNode second = head;
        second.setNext(flipTwoNodeRecursion(first.getNext()));
        first.setNext(second);

        return first;
    }

    /**
     * while方式太复杂，需要记录上一次的最后一个点，然后计算下一个待计算的点。 计算两个点的时候，两个点的指针需要再计算一下。
     * @param head
     * @return
     */
    public ListNode flipTwoNodeWhile(ListNode head){
        if(head == null){
            return head;
        }

        if(head.getNext() == null){
            return head;
        }

        ListNode cur = head;
        ListNode before = new ListNode();
        before.setNext(cur.getNext());
        ListNode newHead = before;

        while ((cur != null) && (cur.getNext() != null)){
            ListNode first = cur.getNext();
            ListNode second = cur;
            second.setNext(first.getNext());
            first.setNext(second);

            before.setNext(first);
            before = second;
            cur = second.getNext();
        }

        return newHead.getNext();
    }

    public static void main(String[] args) {
        TwoNodeFlip twoNodeFlip = new TwoNodeFlip();
        ListNode head = new ListNode();
        ListNode before = head;
        before.setValue(-1);
        for (int i = 0; i < 10; i++) {
            ListNode node = new ListNode();
            node.setValue(i);
            node.setNext(null);
            before.setNext(node);
            before = node;
        }
//        ListNode listNode = twoNodeFlip.flipTwoNodeRecursion(head);
        ListNode listNode = twoNodeFlip.flipTwoNodeWhile(head);

        printList(listNode);

    }

    private static void printList(ListNode listNode) {
        while (listNode != null){
            System.out.println(listNode.getValue());
            listNode = listNode.getNext();
        }
    }
}
