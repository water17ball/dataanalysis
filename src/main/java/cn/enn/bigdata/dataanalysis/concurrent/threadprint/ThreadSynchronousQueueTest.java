package cn.enn.bigdata.dataanalysis.concurrent.threadprint;

import java.util.concurrent.SynchronousQueue;


/**
 * 阻塞队列的问题：
 *  * 因为put和take都是一个门，有可能A刚打印完在等待，下一个B刚刚解锁还没打印，那么A又获取到锁又继续打印，造成AA打印了2次。
 *  synchronousQueue与ArrayBlockingQueue类似，都是阻塞队列，都是在put和take上阻塞。
 */
public class ThreadSynchronousQueueTest {

//private ArrayBlockingQueue<Long> arrayBlockingQueue = new ArrayBlockingQueue<>(1);
private SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
    public void printChar() {

        Runnable runnable1 = () -> {
            for (char i = 'a'; i <= 'z'; i++) {
                try {
                    synchronousQueue.take();
                    System.out.println(i);

                } catch (Exception e) {

                } finally {

                }
            }
        };

            Runnable runnable2 = () -> {
                for (char i = 'A'; i <= 'Z'; i++) {
                    try {
                        synchronousQueue.put(1);
                        System.out.println(i);
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

    public static void main(String[] args) {
        ThreadSynchronousQueueTest threadTest = new ThreadSynchronousQueueTest();
        threadTest.printChar();

    }
}
