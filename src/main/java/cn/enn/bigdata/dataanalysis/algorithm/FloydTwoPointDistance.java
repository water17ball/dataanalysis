package cn.enn.bigdata.dataanalysis.algorithm;

/**
 * Floyd算法又称为插点法，
 * 是一种利用动态规划的思想寻找给定的加权图中多源点之间最短路径的算法，与Dijkstra算法类似。
 */
public class FloydTwoPointDistance {

    /**
     * 利用动态规划，保留两个节点间的最短距离，同时不断通过媒介节点，看是否能够缩短距离
     *
     * @param a
     * @param num
     */
    public void getTwoPointDistance(int[][] a, int num) {
        for (int k = 0; k < num; k++) {//针对于每一个k，a[i][j]通过k的媒介是否可以缩短路径，如果可以就保留通过k媒介得到的最短路径.
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (a[i][j] > (a[i][k] + a[k][j])) {
                        System.out.println("通过" + k + " ,可以让a[" + i + "][" + j + "]缩短距离! a[" + i + "][" + j + "]=" + a[i][j] + " , a[" + i + "][" + k + "] = " + a[i][k] + ", a[" + k + "][" + j + "] = " + a[k][j]);
                        a[i][j] = (a[i][k] + a[k][j]);
                    }
                }
            }
        }

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                System.out.println(a[i][j]);
            }
        }
    }

    public static void main(String[] args) {
        int[] c = {0, 10, 1, 20, 10, 0, 2, 5, 1, 2, 0, 10, 20, 5, 10, 0};
        int n = 4;
        int index = 0;
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = c[index++];
            }
        }

        FloydTwoPointDistance floydTwoPointDistance = new FloydTwoPointDistance();
        floydTwoPointDistance.getTwoPointDistance(a, n);


    }
}
