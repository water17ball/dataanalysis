package cn.enn.bigdata.dataanalysis.algorithm;

/**
 * 最长公共子序列（元素不一定相邻）（序列）
 * 比如
 * A：1357
 * B：1235
 * 最长公共子序列为135.
 * <p>
 * 找出公式：
 * 0                            when i==0 || j==0
 * c[i, j] = c[i-1, j-1] + 1              when a[i] == b[j]
 * max( c[i-1,j] , c[i, j-1] )  when a[i] != b[j]
 */
public class LongestCommonSequence {

    public void exhaustionMethod(int[] a, int[] b) {//这样不是全面的穷举法，因为a数列只是按照连续的进行比对的. 算是一种最长公共子串substring
        int maxLength = 0;
        for (int i = 0; i < a.length; i++) {
            System.out.println("i:" + i);
            int curMax = 0;
            int iTemp = i;
            for (int j = 0; j < b.length; j++) {
                System.out.println("j:" + j);
                if (a[iTemp] == b[j]) {
                    curMax++;
                    iTemp++;
                }
                if (iTemp >= a.length) {
                    break;
                }
            }
            if (curMax > maxLength) {
                maxLength = curMax;
            }
        }
        System.out.println(maxLength);
    }

    public void recursiveMethod(int[] a, int[] b) {
        int[][] c = new int[a.length + 1][b.length + 1];
//        for (int i = 0; i <= a.length; i++) {
//            c[i][0] = 0;
//        }
//        for (int j = 0; j <= b.length; j++) {
//            c[0][j] = 0;
//        }
        int maxLength = lcs(a, b, c, a.length, b.length);
        System.out.println(maxLength);
    }

    /**
     * 递归方法（自顶向下）
     *
     * @param a
     * @param b
     * @param c
     */
    private int lcs(int[] a, int[] b, int[][] c, int i, int j) {
        if ((i <= 0) || (j <= 0)) {
            return 0;
        }

        if (a[i - 1] == b[j - 1]) {
            return lcs(a, b, c, i - 1, j - 1) + 1;
        } else {
            return Math.max(lcs(a, b, c, i - 1, j), lcs(a, b, c, i, j - 1));
        }
    }

    /**
     * 迭代方法（自底向上）
     * <p>
     * 注意：需要循环(m+1)*(n+1)次，将(m+1)*(n+1)个空间都填满。 为了迭代，需要将i或者j为0的时候，先填好，作为基础迭代数据。
     *
     * @param a
     * @param b
     */
    public void iterationMethod(int[] a, int[] b) {
        int[][] c = new int[a.length + 1][b.length + 1];
//        for (int i = 0; i <= a.length; i++) {//为了迭代，需要将i或者j为0的时候，先填好，作为基础迭代数据。
//            c[i][0] = 0;
//        }
//        for (int j = 0; j <= b.length; j++) {
//            c[0][j] = 0;
//        }

        for (int i = 0; i <= a.length; i++) {
            for (int j = 0; j <= b.length; j++) {
                if ((i == 0) || (j == 0)) {//为了迭代，需要将i或者j为0的时候，先填好，作为基础迭代数据。
                    c[i][j] = 0;
                } else {
                    if (a[i - 1] == b[j - 1]) {//需要到1之后，a和b的数据落后需要1个。
                        c[i][j] = c[i - 1][j - 1] + 1;
                    } else {
                        c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
                    }
                }
            }
        }
        System.out.println(c[a.length][b.length]);
        for (int i = 0; i <= a.length; i++) {
            System.out.println();
            for (int j = 0; j <= b.length; j++) {
                System.out.print(" " + c[i][j]);
            }
        }
        System.out.println();

        PrintLCS(c, a, b, a.length, b.length);
    }

    /**
     * 打印LCS的序列（也是一种动态规划，可以向左、向上、向左上移动，移动一步之后又变成了相同的子问题）
     * 1.从最大序列值开始，如果可以(i-1)&&(j-1) == maxValue -1说明a[i],b[j]是相等的
     *
     * @param c
     * @param i
     * @param j
     */
    public void PrintLCS(int[][] c, int[] a, int[] b, int i, int j) {
        if ((i <= 0) || (j <= 0)) {
            return;
        }

        if (c[i - 1][j - 1] == (c[i][j] - 1)) {
            System.out.println("a[" + (i - 1) + "] = " + a[i - 1]);
//            System.out.println("b[" + j + "] = " + b[j-1]);
            PrintLCS(c, a, b, i - 1, j - 1);
        } else if (c[i - 1][j] == c[i][j]) {
            PrintLCS(c, a, b, i - 1, j);
        } else if (c[i][j - 1] == c[i][j]) {
            PrintLCS(c, a, b, i, j - 1);
        }
    }

    public static void main(String[] args) {

        LongestCommonSequence longestCommonSequence = new LongestCommonSequence();
        int[] a = {1, 0, 3, 4, 5, 7};
        int[] b = {1, 2, 3, 5, 6};
//        int[] a = {1, 2, 3,4};
//        int[] b = {1, 3, 4, 2};
        longestCommonSequence.exhaustionMethod(a, b);
        longestCommonSequence.recursiveMethod(a, b);
        longestCommonSequence.iterationMethod(a, b);
    }
}
