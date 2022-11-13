package cn.enn.bigdata.dataanalysis.algorithm;

/**
 * 换钱的算法
 * 总额N， 有多种面值a[] (例如1,2,5，面值不重复),
 * 问一共有多少种换钱的方式。
 */
public class ChangeMoney {

    public int change(int n, int[] a, int aLen){
        //aLen就是面值的索引，一开始为数组个数
        int num = 0;
        //针对每一种面值，进行拆分
        for (int i = aLen;i > 0; i--) {
            if((n-a[i-1]) > 0){
                num += change(n - a[i-1], a, i);
            }
            else if((n-a[i-1]) == 0){
                num += 1;
            }
            else {//不可能的情况。
                num += 0;
            }
        }

        return num;
    }

    public static void main(String[] args) {
//        int n = 105;
//        int[] a = new int[] {1,2,5,10};
        int n = 15;
        int[] a = new int[] {1,5,10,25};
        int aLen = a.length;
        ChangeMoney changeMoney = new ChangeMoney();
        int wayNum = changeMoney.change(n, a, aLen);
        System.out.println(wayNum);
    }
}
