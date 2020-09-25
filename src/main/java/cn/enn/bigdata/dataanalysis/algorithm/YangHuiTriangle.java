package cn.enn.bigdata.dataanalysis.algorithm;

/**
 * yang hui san jiao
 */
public class YangHuiTriangle {

    //
    public void print(int level) {
        int[][] ret = new int[level][level];//一个数组就够了。再加上双层for循环

        for (int i = 0; i < level; i++) {
            for (int j = 0; j <= i; j++) {
                if ((i == 0) || (j == 0) || (i == j)) {
                    ret[i][j] = 1;
                } else {
                    ret[i][j] = ret[i - 1][j - 1] + ret[i - 1][j];
                }
            }
        }


        for (int i = 0; i < level; i++) {
            System.out.println();
            for (int t = 0; t < (level - i); t++) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print(ret[i][j] + " ");
            }
        }
    }

    public static void main(String[] args) {
        YangHuiTriangle y = new YangHuiTriangle();
        y.print(8);
    }
}
