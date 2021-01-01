package cn.enn.bigdata.dataanalysis.concurrent;

import java.sql.SQLOutput;

/**
 * 按序打印
 * 我们提供了一个类：
 * <p>
 * public class Foo {
 * public void first() { print("first"); }
 * public void second() { print("second"); }
 * public void third() { print("third"); }
 * }
 * 三个不同的线程将会共用一个 Foo 实例。
 * <p>
 * 线程 A 将会调用 first() 方法
 * 线程 B 将会调用 second() 方法
 * 线程 C 将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: "firstsecondthird"
 * 解释:
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 second() 方法，线程 C 将会调用 third() 方法。
 * 正确的输出是 "firstsecondthird"。
 * 示例 2:
 * <p>
 * 输入: [1,3,2]
 * 输出: "firstsecondthird"
 * 解释:
 * 输入 [1,3,2] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 third() 方法，线程 C 将会调用 second() 方法。
 * 正确的输出是 "firstsecondthird"。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 我们提供一个类：
 * <p>
 * class FooBar {
 * public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 * }
 * <p>
 * public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 * }
 * }
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 * // 可重入锁 + Condition
 * class FooBar4 {
 * private int n;
 * <p>
 * public FooBar4(int n) {
 * this.n = n;
 * }
 * Lock lock = new ReentrantLock(true);
 * private final Condition foo = lock.newCondition();
 * volatile boolean flag = true;
 * public void foo(Runnable printFoo) throws InterruptedException {
 * for (int i = 0; i < n; i++) {
 * lock.lock();
 * try {
 * while(!flag) {
 * foo.await();
 * }
 * printFoo.run();
 * flag = false;
 * foo.signal();
 * }finally {
 * lock.unlock();
 * }
 * }
 * }
 * <p>
 * public void bar(Runnable printBar) throws InterruptedException {
 * for (int i = 0; i < n;i++) {
 * lock.lock();
 * try {
 * while(flag) {
 * foo.await();
 * }
 * printBar.run();
 * flag = true;
 * foo.signal();
 * }finally {
 * lock.unlock();
 * }
 * }
 * }
 * }
 */
public class OrderedPrint {
    OrderedPrint() {

    }

    volatile int curIndex = 0;

    public void printFirst() throws InterruptedException {
        System.out.println("first started!");
        while (curIndex != 0) {
            Thread.sleep(50);
        }
        System.out.println("first");
        curIndex = 1;
    }

    public void printSecond() throws InterruptedException {
        System.out.println("second started!");
        while (curIndex != 1) {//可以使用可重入锁+等待条件condition
            Thread.sleep(50);
        }
        System.out.println("second");
        curIndex = 2;
    }

    public void printThird() throws InterruptedException {
        System.out.println("third started!");
        while (curIndex != 2) {
            Thread.sleep(50);
        }
        System.out.println("third");
        curIndex = 0;
    }

    public void printByIndex(int index) throws InterruptedException {
        switch (index) {
            case 0:
                printFirst();
                break;
            case 1:
                printSecond();
                break;
            case 2:
                printThird();
                break;
        }
    }

    public static void main(String[] args) {
        int[] orderArray = {2, 3, 1};
        OrderedPrint orderedPrint = new OrderedPrint();
        Runnable runnable0 = new Runnable() {
            private int curIndex = 0;

            @Override
            public void run() {
                try {
                    orderedPrint.printByIndex(curIndex);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable runnable1 = new Runnable() {
            private int curIndex = 1;

            @Override
            public void run() {
                try {
                    orderedPrint.printByIndex(curIndex);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable runnable2 = new Runnable() {
            private int curIndex = 2;

            @Override
            public void run() {
                try {
                    orderedPrint.printByIndex(curIndex);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        for (int i = 0; i < orderArray.length; i++) {
            System.out.println("curIndex:" + i + ", num:" + orderArray[i]);
            Runnable runnable = null;
            switch (orderArray[i] - 1) {
                case 0:
                    runnable = runnable0;
                    break;
                case 1:
                    runnable = runnable1;
                    break;
                case 2:
                    runnable = runnable2;
                    break;

            }
            Thread thread = new Thread(runnable);
            thread.start();
        }


    }


}
