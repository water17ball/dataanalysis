package cn.enn.bigdata.dataanalysis.algorithm;

import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O ( 1 ) O(1)O(1) 的平均时间复杂度运行。
 * ————————————————
 * 哈希表+双向链表
 * 1）要实现O(1)时间复杂度，必须使用hash表
 * 2）要实现简单排序，使用队列（最新的插入head，最旧的从tail删除）。为了实现中间的元素被再次调用，而且满足O(1)复杂度，使用双向链表（双指针）完成快速的节点摘除。
 *
 */
public class LRU {
    DLinkedNode head;
    DLinkedNode tail;
    Map<Integer, DLinkedNode> nodeMap;
    int capacity;
    int size;

    LRU(int inputCapacity){
        capacity = inputCapacity;
        size = 0;
        nodeMap = new HashMap<>(capacity);
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }




    /**
     * 双向链表的节点
     */
    @Data
    private class DLinkedNode{
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        DLinkedNode(){
            key = 0;
            value = 0;
            prev = null;
            next = null;
        }

        DLinkedNode(int inputKey, int inputValue){
            key = inputKey;
            value = inputValue;
            prev = null;
            next = null;
        }

        @Override
        public String toString(){
            return key + " : " + value;
        }
    }


    int get(int key){
        //1.获取数值
//        Optional<DLinkedNode> value = Optional.ofNullable(nodeMap.get(key));
//        value.ifPresent();
        DLinkedNode value = nodeMap.get(key);
        if(value == null){
            return -1;
        }

        //2.放到头部
        putHead(value);


        return  value.value;
    }


    void put(int inputKey, int inputValue){
        //1.先找到是否存在已有的节点，
        DLinkedNode node = nodeMap.get(inputKey);

        // 2.有则放到头部（并且修改value），
        if(node != null){
            node.setValue(inputValue);
            putHead(node);
        }

        // 3.没有则新建一个放到头部。并且放入hashmap中。
        if(node == null){
            putHead(new DLinkedNode(inputKey, inputValue));
        }

        //4.判断是否查过capacity大小，超过则删除tail的节点，并且删除hash表中的内容。
        if(size < capacity){
            size++;
            return;
        }

        removeTail();


    }

    /**
     * 从头部插入节点
     * 如果是已有节点，则需要变更前后节点的指针；如果是新节点，则只处理自身指针
     * @param node
     */
    private void putHead(DLinkedNode node) {
        //新节点
        if(ObjectUtils.isEmpty(node.getPrev()) && ObjectUtils.isEmpty(node.getNext())){
            DLinkedNode second = head.next;
                node.prev = head;
                node.next = second;
                second.prev = node;
                head.next = node;

        }
        else {
            if(node.prev == head){
                //是头部，则不需要调整
                return;
            }

            //节点原前后节点指针
            DLinkedNode prev = node.prev;
            DLinkedNode next = node.next;
            prev.next = next;
            next.prev = prev;


            //节点新的前后指针
            DLinkedNode second = head.next;
            node.prev = head;
            node.next = second;
            second.prev = node;
            head.next = node;

        }

        nodeMap.put(node.getKey(), node);
    }

    /**
     * 删除尾部的节点.
     */
    private void removeTail() {
        if(head.next == tail){
            //空则跳出
            return;
        }

        DLinkedNode node = tail.prev;
        DLinkedNode prev = node.prev;
        prev.next = tail;
        tail.prev = prev;

        //hashmap
        nodeMap.remove(node.getKey());

    }

    void print(){
        System.out.println("打印hashmap:");
        nodeMap.entrySet().forEach(e -> System.out.println(e.getKey() + " : " +e.getValue().toString()));
        System.out.println("打印链表");
        DLinkedNode curNode = head.getNext();
        while (curNode != tail){
            System.out.print(curNode.getKey() + " : " + curNode.getValue() + " -> ");
            curNode = curNode.getNext();
        }
        System.out.println();
    }


    public static void main(String[] args) {
        LRU lru = new LRU(2);
        lru.put(1,1);
        lru.put(2,2);
        lru.get(2);
        lru.put(3,3);
        lru.put(4,4);
        lru.get(3);
        lru.put(5,5);

        lru.print();

    }
}
