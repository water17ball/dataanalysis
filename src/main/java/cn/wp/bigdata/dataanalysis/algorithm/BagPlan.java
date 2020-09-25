package cn.wp.bigdata.dataanalysis.algorithm;

/**
 * 适用于动态规划的问题必须满足最优化原理、无后效性和重叠性。
 * (1) 最优化原理（最优子结构性质）：一个最优化策略具有这样的性质，不论过去状态和决策如何，对前面的决策所形成的状态而言，余下的决策必须构成最优策略。简而言之，一个最优化策略的子策略总是最优的。一个问题满足最优化原理又称其具有最优子结构性质。
 * (2) 无后效性：将各阶段按照一定的次序排列好之后，对于某个给定的阶段状态，它以前各阶段的状态无法直接影响它未来的决策，而只能通过当前的这个状态。换句话说，每个状态都是过去历史的一个完整总结。这就是无后向性，又称无后效性。
 * (3) 子问题的重叠性：动态规划将原来具有指数级时间复杂度的搜索算法改进成了具有多项式时间复杂度的算法。
 * 其中的关键在于解决冗余，这就是动态规划算法的根本目的。动态规划实质上是一种以空间换时间的技术，它在实现的过程中，不得不存储产生过程中的各种状态，所以它的空间复杂度要大于其他算法。
 */
public class BagPlan {

    /**
     * 0-1背包问题
     * 编写冗余表，在加入当前物品的情况，每种容量的最大价值都计算并填入表中。供后面的物品是否添加作参考。
     */
    public void bag01(int T, int n, int[] w, int[] v) {
        //动态规划的最大价值的，在第i个物品存在的情况下，容量为t时候的最大价值
        int[][] s = new int[n][T + 1];

        //初始化，第一个物品填入时的值
        for (int t = 0; t <= T; t++) {
            if (t < w[0]) {
                s[0][t] = 0;
            } else {
                s[0][t] = v[0];
            }
        }

        for (int i = 1; i < n; i++) {//依次判断第2个到n个物品填入
            for (int t = 0; t <= T; t++) {
                if (t == 0) {//没有空间时，填入0
                    s[i][t] = 0;
                } else {//比较上一行
                    if (t >= w[i]) {
                        if ((s[i - 1][t - w[i]] + v[i]) > s[i - 1][t]) {
                            s[i][t] = s[i - 1][t - w[i]] + v[i];
                        } else {
                            s[i][t] = s[i - 1][t];
                        }
                    } else {
                        s[i][t] = s[i - 1][t];
                    }
                }
            }
        }

        System.out.println("最大值为:" + s[n - 1][T]);
        int maxValue = s[n - 1][T];
        int t = T;
        int g = n - 1;
        int[] res = new int[5];
        //打印出哪些物品被选中
        while (g > 0) {
            if (t - w[g] >= 0) {
                if (s[g][t] == (s[g - 1][t - w[g]] + v[g])) {//是被选中而添加的，则打印。
                    res[g] = 1;
                    t = t - w[g];
                    g--;
                } else {
                    g--;
                }
            } else {
                g--;
            }

        }
        assert (g == 0);//下面的0可以被g替换

        if (s[0][t] > 0) {
            res[0] = 1;
        }

        //打印第i个物品是否放入包中
        System.out.println("打印物品i是否放入包中:");
        for (int r = 1; r <= n; r++) {
            System.out.print(r + " ");
        }
        System.out.println();
        for (int re : res) {
            System.out.print(re + " ");
        }
    }

    public static void main(String[] args) {
        int[] w = {4, 5, 6, 2, 2};
        int[] v = {6, 4, 5, 3, 6};

        //容量T
        int T = 10;
        //物品个数
        int n = 5;

        BagPlan bagPlan = new BagPlan();
        bagPlan.bag01(T, n, w, v);

    }
}
