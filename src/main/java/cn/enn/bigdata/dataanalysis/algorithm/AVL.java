package cn.enn.bigdata.dataanalysis.algorithm;


/**
 * 平衡二叉树
 * 参考文章：
 * https://blog.csdn.net/m0_62969222/article/details/125054875
 */
public class AVL {
    private Node root;

    static class Node{
        public Node(Integer v){
            this.value = v;
        }
        public Integer value;
        Node left;
        Node right;

        /**
         * 平衡二叉树，查看数的高度
         */
        public int getHeight(Node node){
            if(node == null){
                return 0;
            }
            return getHeight(node.left) + getHeight(node.right);
        }

        public boolean isBalance(Node node){
            if(node == null){
                return true;
            }

            return getHeight(node.left) == getHeight(node.right);
        }

        /**
         * 返回平衡因子
         * =0 平衡
         * >0 左树大于右树
         * <0 右树大于左树
         * @param node
         * @return
         */
        public int getBalanceFactor(Node node){
            if(node == null){
                return 0;
            }

            return getHeight(node.left) - getHeight(node.right);
        }

        /**
         * 以node为根节点的树进行左旋
         * @param node
         */
        public Node leftRotate(Node node){
            //左旋，说明右树长
            Node curNode = node.right;
            node.right = curNode.left;
            curNode.left = node;
            return curNode;

        }

        /**
         * 以node为根节点的树进行右旋
         * @param node
         */
        public Node rightRotate(Node node){
            Node curNode = node.left;
            node.left = curNode.right;
            curNode.right = node;
            return curNode;
        }

        public Node addNode(Node root, Integer newValue){
            if(root == null){
                return new Node(newValue);
            }
            //1.将节点根据已有节点数值放置到叶子节点
            if(root.value <= newValue){
                addNode(root.left, newValue);
            }
            else if((root.value > newValue)){
                addNode(root.right, newValue);
            }

            //2.开始评估：是否平衡，不平衡将采取的选择方法
            int rootBalanceFactor = getBalanceFactor(root);
            if((rootBalanceFactor > 0) && (getBalanceFactor(root.left) > 0)){
                root = rightRotate(root);
            }
            else if((rootBalanceFactor <= 0) && (getBalanceFactor(root.right) > 0)){
                root = leftRotate(root);
            }
            /**
             * LR和RL型的需要两次旋转，选旋转内部不平衡，再旋转外部的不平衡。
             */
            else if((rootBalanceFactor > 0) && (getBalanceFactor(root.left) <= 0)){
                root.left = root.leftRotate(root.left);//左旋之后，root.left节点变化了，赋值为新的左节点
                root = rightRotate(root);//进行右旋。将左侧长的部分平衡掉。
            }
            else if((rootBalanceFactor <= 0) && (getBalanceFactor(root.right) <= 0)){
                root.right = rightRotate(root.right);//右旋之后，root.right节点变化了，赋值为新的右节点
                root = leftRotate(root);//进行左旋。将右侧侧长的部分平衡掉。
            }

            return root;
        }


    }
}
