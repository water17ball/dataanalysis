package cn.enn.bigdata.dataanalysis.algorithm;

/**
 * 最长公共子串（需要字符相连）
 * 注意：需要回溯，情况非常多。需要使用动态规划算法。
 * * 比如
 * * A：1357
 * * B：1235
 * * 最长公共子串为35.
 * 找出公式：
 * 0                                    when i==0 || j==0
 * c[i, j] = c[i-1, j-1] + 1            when a[i] == b[j]
 * 0                                    when a[i] != b[j]
 * <p>
 * 算法设计：
 * 空间复杂度：1）递归算法：只有记录最大长度的int。 2）迭代算法iteration：需要(m+1)*(n+1)个空间，存放迭代过程中的子问题的结果
 * 时间复杂度：m*n
 */
public class LongestCommonSubString {


    /**
     * 使用递归方法不太好。因为不像子序列那样，可以将最大值一直传递并且累加。
     * 所以需要一个当前最大值allMax，和一个连续累加值curMax，进行传递。
     * 同时，在发现有相同字符的时候，更新一下当前最大值allMax。保证得出一个全局的最大allMax。
     *
     * @param a
     * @param b
     */
    public void recursiveMethod(int[] a, int[] b) {
        int maxLength = LCSS(a, b, a.length, b.length, 0, 0);
        System.out.println(maxLength);
    }

    private int LCSS(int[] a, int[] b, int aIndex, int bIndex, int curMax, int allMax) {
        if ((aIndex == 0) || (bIndex == 0)) {
            return allMax;
        } else {
            if (a[aIndex - 1] == b[bIndex - 1]) {
                if (allMax == curMax) {
                    allMax = allMax + 1;
                }
                return (LCSS(a, b, aIndex - 1, bIndex - 1, 1 + curMax, allMax));
            } else {
                return Math.max(LCSS(a, b, aIndex, bIndex - 1, 0, allMax), LCSS(a, b, aIndex - 1, bIndex, 0, allMax));

            }
        }
    }

    /**
     * 迭代法：
     * 构建(m+1)*(n+1)的数组，保存中间过程。
     * * 找出公式：
     * * 0                                    when i==0 || j==0
     * * c[i, j] = c[i-1, j-1] + 1            when a[i] == b[j]
     * * 0                                    when a[i] != b[j]
     *
     * @param a
     * @param b 空间复杂度(m+1)*(n+1)，从0开始填充
     */
    public void iterativeMethod(int[] a, int[] b) {
        int[][] c = new int[a.length + 1][b.length + 1];
        int curMax = 0;
        for (int i = 0; i <= a.length; i++) {
            for (int j = 0; j <= b.length; j++) {
                if ((0 == i) || (0 == j)) {
                    c[i][j] = 0;
                } else {
                    if (a[i - 1] == b[j - 1]) {
                        c[i][j] = c[i - 1][j - 1] + 1;
                        if (c[i][j] > curMax) {
                            curMax = c[i][j];
                        }
                    } else {
                        c[i][j] = 0;
                    }
                }

            }
        }

        //打印最大值，取其中的最大值，不一定是最后的那个值（因为不向后延续）。可能是中间过程中的一个最大值。
        System.out.println(curMax);
    }

    public static void main(String[] args) {
        int[] a = {1, 0, 3, 4, 5, 6, 7};
        int[] b = {1, 2, 3, 5, 6};

        LongestCommonSubString longestCommonSubString = new LongestCommonSubString();
        longestCommonSubString.recursiveMethod(a, b);
        longestCommonSubString.iterativeMethod(a, b);

    }
}
