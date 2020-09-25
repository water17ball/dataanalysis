package cn.wp.bigdata.dataanalysis.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Fibonacci斐波那锲
 */
public class Fibonacci {
    public long fNRecursive(int n) {
        if ((n > 0) && (n < 3)) {
            return 1L;
        } else {
            return fNRecursive(n - 1) + fNRecursive(n - 2);
        }
    }

    public long fNIteration(int n) {
        List<Long> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i < 2) {
                ret.add(1L);
            } else {
                ret.add(ret.get(i - 2) + ret.get(i - 1));
            }
        }
        return ret.get(n - 1).longValue();

    }

    public long fNIterationSimple(int n) {
        long last1 = 1L;
        long last2 = 1L;

        long newLong = 0L;

        if (n < 2) {
            return 1L;
        } else {
            for (int i = 2; i <= n; i++) {
                newLong = last1 + last2;
                //重新赋值
                last1 = last2;
                last2 = newLong;
            }
        }

        return newLong;


    }

    public static void main(String[] args) {
        Fibonacci f = new Fibonacci();
        long t0 = System.nanoTime();
        System.out.println(f.fNRecursive(6));
        long t1 = System.nanoTime();
        System.out.println(f.fNIteration(6));
        long t2 = System.nanoTime();
        System.out.println(f.fNIterationSimple(6));
        long t3 = System.nanoTime();
        System.out.println("t0:" + (t1 - t0));
        System.out.println("t1:" + (t2 - t1));
        System.out.println("t2:" + (t3 - t2));

    }
}
