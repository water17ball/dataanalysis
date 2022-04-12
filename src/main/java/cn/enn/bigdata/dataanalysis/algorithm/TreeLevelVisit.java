package cn.enn.bigdata.dataanalysis.algorithm;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * 层次遍历树结构
 */
@Data
public class TreeLevelVisit {

    @Data
    class Node {
        public int value;
        public Node leftSon;
        public Node rightSon;
    }


    /**
     * 根据int数组，构建层次树
     *
     * @param v 用到的数据结构：
     *          1）入参数组
     *          2）节点队列，用于层次构建
     *          3）当前节点
     *          4）返回的根节点
     * @return
     */
    private Node buildLevelTree(int[] v) {
        List<Node> nodeQueue = new LinkedList<>();
        Node root = null;
        Node curNode = null;
        int totalLength = v.length;
        int curLength = 0;
        while (curLength < totalLength) {
            if (nodeQueue.isEmpty()) {//列表中空是没有放内容的初始时刻
                Node tempNode = new Node();
                tempNode.setValue(v[curLength]);
                nodeQueue.add(tempNode);

                root = tempNode;

                curLength++;//自增
            } else {//列表不空则拿出一个节点为当前节点
                curNode = ((LinkedList<Node>) nodeQueue).removeFirst();
                if (curLength < totalLength) {
                    Node tempNode = new Node();
                    tempNode.setValue(v[curLength]);
                    nodeQueue.add(tempNode);
                    curNode.setLeftSon(tempNode);
                    curLength++;
                }
                if (curLength < totalLength) {
                    Node tempNode = new Node();
                    tempNode.setValue(v[curLength]);
                    nodeQueue.add(tempNode);
                    curNode.setRightSon(tempNode);
                    curLength++;
                }
            }
//            curLength++;//自增  要不要整体的自增，要全盘考虑，如果部分自增复杂，则在各个分支部分中各自处理自增
        }

        return root;
    }

    /**
     * 层次遍历树
     *
     * @param root 用到的数据结构：
     *             1）队列（存储未被访问的节点）
     *             2）记录每个层次个数（或者记录当前层数，以及下一层的数目，2个变量）
     *             3）当前节点（保存从队列中拿出来的当前节点）
     */
    public void visitLevelTree(Node root) {
        List<Node> nodeList = new LinkedList<>();//存储未被访问的节点
        Node curNode = null;

        int curLevelNum = 0;//当前层数
        int nextLevelNum = 0;//下一层数

        nodeList.add(root);
        curLevelNum++;

        System.out.println("打印开始");
        System.out.print("[");

        while (!nodeList.isEmpty()) {
            curNode = ((LinkedList<Node>) nodeList).removeFirst();
            if (curLevelNum == 0) {//说明上一层已经完成了
                curLevelNum = nextLevelNum;//再接着访问下一层
                nextLevelNum = 0;
                System.out.println("]");
                System.out.print("[");
            }
            curLevelNum--;//弹出一个，就减少一个

            System.out.print(curNode.getValue() + " ");
            if (curNode.getLeftSon() != null) {
                nodeList.add(curNode.getLeftSon());
                nextLevelNum++;
            }
            if (curNode.getRightSon() != null) {
                nodeList.add(curNode.getRightSon());
                nextLevelNum++;
            }
        }

        System.out.println("]");

    }

    /**
     * 层次遍历树-第二个版本
     *  （与第一个版本在循环条件判断、弹出节点时机、循环条件重置方面有不同）
     * @param root 用到的数据结构：
     *             1）队列（存储未被访问的节点）
     *             2）记录每个层次个数（或者记录当前层数，以及下一层的数目，2个变量）
     *             3）当前节点（保存从队列中拿出来的当前节点）
     */
    public void visitLevelTreed2nd(Node root) {
        List<Node> nodeList = new LinkedList<>();//存储未被访问的节点
        Node curNode = null;

        int curLevelNum = 0;//当前层数
        int nextLevelNum = 0;//下一层数

        nodeList.add(root);
        curLevelNum++;

        System.out.println("打印开始");
        System.out.print("[");

        curNode = ((LinkedList<Node>) nodeList).removeFirst();
        while ((curNode != null) && (curLevelNum != 0)) {

            if (curNode.getLeftSon() != null) {
                nodeList.add(curNode.getLeftSon());
                nextLevelNum++;
            }
            if (curNode.getRightSon() != null) {
                nodeList.add(curNode.getRightSon());
                nextLevelNum++;
            }

            System.out.print(curNode.getValue() + " ");
            curLevelNum--;//弹出一个，就减少一个

            if (curLevelNum == 0) {//说明上一层已经完成了
                curLevelNum = nextLevelNum;//再接着访问下一层
                nextLevelNum = 0;

                if(curLevelNum != 0){
                    curNode = ((LinkedList<Node>) nodeList).removeFirst();
                    System.out.println("]");
                    System.out.print("[");
                }
                else {
                    break;
                }
            }
            else {
                curNode = ((LinkedList<Node>) nodeList).removeFirst();
            }


        }

        System.out.println("]");

    }


    public static void main(String[] args) {
        int[] v = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        TreeLevelVisit visit = new TreeLevelVisit();
        //构建层次树
        Node root = visit.buildLevelTree(v);
        System.out.println(root);

        visit.visitLevelTree(root);
        visit.visitLevelTreed2nd(root);
    }

}
