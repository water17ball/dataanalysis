package com.example.demo.test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 阻塞队列的问题：
 * 因为put和take都是一个门，有可能A刚打印完在等待，下一个B刚刚解锁还没打印，那么A又获取到锁又继续打印，造成AA打印了2次。
 * Lock也有这个问题，毕竟condition等待的也是一个门的条件。
 * 最好的还是while循环，这样每次检查条件，不符合的继续等待，符合的打印一次就改变条件让自己阻塞。
 */
public class ThreadBlockingQueueTest {

    private ArrayBlockingQueue<Long> arrayBlockingQueue = new ArrayBlockingQueue<>(1);
    private ArrayBlockingQueue<Long> arrayBlockingQueueDoubleOne = new ArrayBlockingQueue<>(1);
    private ArrayBlockingQueue<Long> arrayBlockingQueueDoubleTwo = new ArrayBlockingQueue<>(1);
    public void printChar() {

        Runnable runnable1 = () -> {
            for (char i = 'a'; i <= 'z'; i++) {
                try {
                    arrayBlockingQueue.put(System.currentTimeMillis());
                    System.out.println(i);

                } catch (Exception e) {

                } finally {

                }
            }
        };

        Runnable runnable2 = () -> {
            for (char i = 'A'; i <= 'Z'; i++) {
                try {
                    System.out.println(i);
                    Long take = arrayBlockingQueue.take();
                }
                catch (Exception e){

                }
                finally {

                }
            }
        };

        new Thread(runnable1).start();
        new Thread(runnable2).start();


    }

    public void printCharDoubleBlock() {

        Runnable runnable1 = () -> {
            for (char i = 'a'; i <= 'z'; i++) {
                try {
                    arrayBlockingQueueDoubleOne.take();
                    System.out.println(i);
                    arrayBlockingQueueDoubleTwo.put(System.currentTimeMillis());

                } catch (Exception e) {

                } finally {

                }
            }
        };

        Runnable runnable2 = () -> {
            for (char i = 'A'; i <= 'Z'; i++) {
                try {
                    arrayBlockingQueueDoubleTwo.take();
                    System.out.println(i);
                    arrayBlockingQueueDoubleOne.put(System.currentTimeMillis());
                }
                catch (Exception e){

                }
                finally {

                }
            }
        };

        new Thread(runnable1).start();
        new Thread(runnable2).start();
        try {
            arrayBlockingQueueDoubleOne.put(System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        ThreadBlockingQueueTest threadTest = new ThreadBlockingQueueTest();
        threadTest.printChar();

        System.out.println("--------------------new ------------------");
        threadTest.printCharDoubleBlock();

    }
}
