package cn.enn.bigdata.dataanalysis.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 *
 * 实现 LFUCache 类：
 *
 * LFUCache(int capacity) - 用数据结构的容量capacity 初始化对象
 * int get(int key)- 如果键key 存在于缓存中，则获取键的值，否则返回 -1 。
 * void put(int key, int value)- 如果键key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 *
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 *
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 * 力扣（LeetCode） 460
 * 链接：https://leetcode.cn/problems/lfu-cache
 *
 * 解题思路
 * 一定先从最简单的开始，根据 LFU 算法的逻辑，我们先列举出算法执行过程中的几个显而易见的事实：
 *
 * 1、调用get(key)方法时，要返回该key对应的val。
 *
 * 2、只要用get或者put方法访问一次某个key，该key的freq就要加一。
 *
 * 3、如果在容量满了的时候进行插入，则需要将freq最小的key删除，如果最小的freq对应多个key，则删除其中最旧的那一个。
 *
 * 好的，我们希望能够在 O(1) 的时间内解决这些需求，可以使用基本数据结构来逐个击破：
 *
 * 1、使用一个HashMap存储key到val的映射，就可以快速计算get(key)。
 *
 * HashMap<Integer, Integer> keyToVal;
 * 2、使用一个HashMap存储key到freq的映射，就可以快速操作key对应的freq。
 *
 * HashMap<Integer, Integer> keyToFreq;
 * 3、这个需求应该是 LFU 算法的核心，所以我们分开说。
 *
 * 3.1、首先，肯定是需要freq到key的映射，用来找到freq最小的key。
 *
 * 3.2、将freq最小的key删除，那你就得快速得到当前所有key最小的freq是多少。想要时间复杂度 O(1) 的话，肯定不能遍历一遍去找，那就用一个变量minFreq来记录当前最小的freq吧。
 *
 * 3.3、可能有多个key拥有相同的freq，所以 freq对key是一对多的关系，即一个freq对应一个key的列表。
 *
 * 3.4、希望freq对应的key的列表是存在时序的，便于快速查找并删除最旧的key。
 *
 * 3.5、希望能够快速删除key列表中的任何一个key，因为如果频次为freq的某个key被访问，那么它的频次就会变成freq+1，就应该从freq对应的key列表中删除，加到freq+1对应的key的列表中。
 *
 * HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
 * int minFreq = 0;
 * 介绍一下这个LinkedHashSet，它满足我们 3.3，3.4，3.5 这几个要求。你会发现普通的链表LinkedList能够满足 3.3，3.4 这两个要求，但是由于普通链表不能快速访问链表中的某一个节点，所以无法满足 3.5 的要求。
 *
 * LinkedHashSet顾名思义，是链表和哈希集合的结合体。链表不能快速访问链表节点，但是插入元素具有时序；哈希集合中的元素无序，但是可以对元素进行快速的访问和删除。
 *
 * 那么，它俩结合起来就兼具了哈希集合和链表的特性，既可以在 O(1) 时间内访问或删除其中的元素，又可以保持插入的时序，高效实现 3.5 这个需求。
 *
 * 综上，我们可以写出 LFU 算法的基本数据结构.
 *
 * 思考：
 * 1）这个算法有意义的第一点：
 *    按照思路，分开步骤编写：先写各个步骤的简要说明。再根据说明的逻辑编程代码
 * 2) 这个算法有意义的第二点：
 *    在写算法的过程中，突然发现最小的频率数leastUseNum，在leastUseNum的频率的集合set中全都升级为高频率之后，最小频率该变为多少呢？是否还得遍历才能知道。
 *    最后还是在纸上画了一下，发现leastUseNum不可能跳变，只会+1，因为这种情况下，leastUseNum频率的也只能增长到下个自然数。
 *
 * 极端情况下：
 * LFU(3)
 * key         5    2    4
 * frequent    2    3    3
 *
 * 此时新加入的频率只能为1，已经达到最大容量，加入后，再删除频率最小的，还是自己。即：新加的始终进不来。
 * 老的也不会被淘汰掉。
 *
 */
public class LFU {
    int leastUseNum;
    Map<Integer, Node> dataMap;
    Map<Integer, Set<Integer>> frequentMap;
    int capacity;
    int size;

    @AllArgsConstructor
    @Data
    @ToString
    private class Node{
        int value;
        int useCount;
    }

    LFU(int inputCapacity){
        leastUseNum = 1;
        capacity = inputCapacity;
        size = 0;

        dataMap = new HashMap<>(capacity);
        frequentMap = new HashMap<>();
    }

    public int get(int key){
        //1.获取数据
        Node node = dataMap.get(key);
        if(node == null){
            return -1;
        }

        //2.标记数据被访问，+1
        addFrequence( node,key, null);


        return node.getValue();
    }


    public void put(int inputKey, int inputValue){
        //1.检查当前值是否存储
        Node node = dataMap.get(inputKey);
        //1.5不存在则为新增
        if(node == null){
            addNewKey(inputKey, inputValue);
        }
        //1.6已有的，要修改value，并且将frequent频率+1
        else {
            //标记数据被访问，+1
            addFrequence( node,inputKey, inputValue);
        }

        //2.检查容量，是否超过，超过则删除最少使用的那个
        if(size < capacity){
            size++;
            return;
        }

        removeLeastFrequentUse();

    }

    /**
     * 删除最小使用的
     */
    private void removeLeastFrequentUse() {
        Set<Integer> set = frequentMap.get(leastUseNum);
         Integer oldKey = (Integer)((LinkedHashSet) set).iterator().next();
         //删除dataMap中元素
        dataMap.remove(oldKey);
        //删除frequentMap
        deleteOldKeyFrequent(oldKey, leastUseNum);
    }


    /**
     * 添加使用频率，包括node和frequentMap
     * @param key
     * @param node
     */
    private void addFrequence(Node node, Integer key, Integer value) {
        int curNodeUseCount = node.getUseCount();
        node.setUseCount(curNodeUseCount + 1);
        if(value != null){
            node.setValue(value);
        }
        //删除之前的set中的key
        deleteOldKeyFrequent(key, curNodeUseCount);

        //添加到新的set中
        addToBiggerFrequent(key, curNodeUseCount);
    }

    private void addToBiggerFrequent(Integer key, int curNodeUseCount) {
        Set<Integer> biggerSet = frequentMap.get(curNodeUseCount + 1);
        if(biggerSet == null){
            biggerSet = new LinkedHashSet<>();
            biggerSet.add(key);
            frequentMap.put(curNodeUseCount + 1, biggerSet);
        }
        else {
            biggerSet.add(key);
        }
    }

    private void deleteOldKeyFrequent(Integer key, int curNodeUseCount) {
        Set<Integer> keys = frequentMap.get(curNodeUseCount);
        keys.remove(key);

        //查看是否需要修改leastUseNum。
        if(keys.size() <= 0){
            if(curNodeUseCount == leastUseNum){
                //最小的set中为空了，则需要+1.  (原来思考当前的没了，不知道频率多少是最低频率。在思考了很久，图上画了一次，才发现最小的频率是比原来+1.不会隔开增长。)
                leastUseNum++;
            }
        }
    }


    private void addNewKey(int inputKey, int inputValue) {
        dataMap.put(inputKey, new Node(inputValue, 1));
        Set<Integer> keySet = frequentMap.get(1);
        if(keySet == null){
            keySet = new LinkedHashSet<>();
            keySet.add(inputKey);
            frequentMap.put(1, keySet);
        }
        else {
            keySet.add(inputKey);
        }

        //设置最小频率
        leastUseNum = 1;
    }

    public void print(){
        dataMap.entrySet().forEach( e -> System.out.println(e.getKey() + " : " + e.getValue().toString()));
    }

    public static void main(String[] args) {
        LFU lfu = new LFU(2);
        lfu.put(1,1);
        lfu.put(2,2);
        lfu.put(3,3);
        lfu.get(2);
        lfu.put(4,4);
        lfu.put(5,5);

        lfu.print();

    }


}
