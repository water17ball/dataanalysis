package cn.enn.bigdata.dataanalysis.algorithm;

/**
 * 单源最短路径算法
 * 基于贪心的Dijkstra算法
 */
public class Dijkstra {
    public static void doDijkstra(int[][]path, int totalNodeNum, int startNode, int endNode){
        int[] shortValue = initShortValue(path,totalNodeNum, startNode);
        int[] visited = new int[totalNodeNum];
        int[] parent = new int[totalNodeNum+1];
        int curNode = startNode;
        boolean found = false;
        while((!found) && (curNode >= 0)) {
            for (int i = 0; i < totalNodeNum; i++) {
                int newValue = shortValue[curNode] + path[curNode][i];
                if((newValue > 0) &&(shortValue[i] > newValue)){
                    shortValue[i] = newValue;//更新最短路径值
                    parent[i] = curNode;//记录父节点
                    if(i == endNode){
                        found = true;
                        break;
                    }
                }
            }
            visited[curNode] = 1;
            curNode = findUnVisitiedShortNode(visited, shortValue);
        }
        printPath(parent, endNode, startNode);
        printValueResut(shortValue, endNode);
    }

    //打印结果数字
    private static void printValueResut(int[] shortValue, int endNode) {
        System.out.println("result value: " + shortValue[endNode]);
    }

    private static void printPath(int[] parent, int endNode, int startNode) {
        int curNode = endNode;
        System.out.println("short path :");
        for (int i = 0; i < parent.length; i++) {
            System.out.println(parent[curNode]);
            curNode = parent[curNode];
            if(curNode == startNode){
                break;
            }
        }
    }

    //找到未被访问的目前最短路径的值的中间节点
    private static int findUnVisitiedShortNode(int[] visitied, int[] shortValue) {
        int min = Integer.MAX_VALUE;
        int min_node = -1;
        for (int i = 0; i < shortValue.length; i++) {
            if((0 == visitied[i]) && (min > shortValue[i])){
                min = shortValue[i];
                min_node = i;
            }
        }
        return min_node;
    }

    //初始化最短路径值的数组
    private static int[] initShortValue(int[][] path, int totalNodeNum,int startNode) {
        int[] result = new int[totalNodeNum];
        for (int i = 0; i < totalNodeNum; i++) {
            result[i] = path[startNode][i];
        }
        return result;
    }


    public static void main(String[] args) {
        int[][] path = {
                {0, 1, Integer.MAX_VALUE,4, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {1, 0, 2,Integer.MAX_VALUE,1, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 2,0,Integer.MAX_VALUE,Integer.MAX_VALUE, 3},
                {4, Integer.MAX_VALUE, Integer.MAX_VALUE,0, 5, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 5, 0, 1},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 3, Integer.MAX_VALUE, 1, 0},
        };
        int total = 6;
        int start = 0;
        int end = 5;
        doDijkstra(path, total, start, end);
    }
}
