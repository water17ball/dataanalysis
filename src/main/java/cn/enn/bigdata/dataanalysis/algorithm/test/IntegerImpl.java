package cn.enn.bigdata.dataanalysis.algorithm.test;

public class IntegerImpl {

    public static int numberOfLeadingZeros(int i) {
        int n = 0;
        //向右移动，因为0都是在左边
        //折半查找，这样效率最高。先检查前16位，再检查剩下的8位，再检查剩下的4位，。。。，最后检查剩下的1位。
        if (i >>> 16 == 0) {
            n += 16;
            i <<= 16;
        }
        if (i >>> 24 == 0) {
            n += 8;
            i <<= 8;
        }
        if (i >>> 28 == 0) {
            n += 4;
            i <<= 4;
        }
        if (i >>> 30 == 0) {
            n += 2;
            i <<= 2;
        }
        if (i >>> 31 == 0) {
            n += 1;
            i <<= 1;
        }
        if (i >= 0) {
            n += 1;
        }

        return n;
    }

    public static void main(String[] args) {
//        int i = 99;
//
//        int n = IntegerImpl.numberOfLeadingZeros(i);
//        System.out.println("IntegerImpl.numberOfLeadingZeros(" + i + ") = " + n);
//        System.out.println("Integer.numberOfLeadingZeros(" + i + ") = " + Integer.numberOfLeadingZeros(i));

        for (int i = 1; i < 100; i++) {
            int n = IntegerImpl.numberOfLeadingZeros(i);
            int n2 = Integer.numberOfLeadingZeros(i);
            System.out.println("IntegerImpl.numberOfLeadingZeros(" + i + ") = " + n);
            System.out.println("Integer.numberOfLeadingZeros(" + i + ") = " + n2);
            if (n != n2) {
                System.out.println("^=======================================^");
            }
        }
    }
}
