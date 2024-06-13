package cn.enn.bigdata.dataanalysis.concurrent.threadprint;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {

//    private CyclicBarrier cyclicBarrie = new CyclicBarrier(2);
//    private volatile int num = 2;
private ReentrantLock reentrantLock = new ReentrantLock();
    Condition condition = null;
    Condition condition2 = null;
    ThreadTest(){
        condition = reentrantLock.newCondition();
        condition2 = reentrantLock.newCondition();
    }

    public void start(){
        reentrantLock.lock();
        condition.signal();
        reentrantLock.unlock();
    }
    public void printChar() {
        Callable<String> callable1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                for (char i = 'A'; i <= 'Z'; i++) {
                    try {
                        reentrantLock.lock();
//                        if(i != 'A')
//                        {
//                            long l = condition.awaitNanos(1000);
//                            System.out.println(l);
//                            if(l > 0){
//
//
//                            }
//                        }

                        System.out.println(i);
                        condition2.signalAll();
                        condition.await();
                    }
                    catch (Exception e){

                    }
                    finally {
                        reentrantLock.unlock();
                    }
                }
                return null;
            }
        };
        Callable<String> callable2 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                for (char i = 'a'; i <= 'z'; i++) {
                    try {
                        reentrantLock.lock();
//                        long l = condition2.awaitNanos(1000);

                            System.out.println(i);
                            condition.signalAll();
                        condition2.await();
                    }
                    catch (Exception e){

                    }
                    finally {
                        reentrantLock.unlock();
                    }
                }
                return null;
            }
        };

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        threadPoolExecutor.submit(callable1);

        threadPoolExecutor.submit(callable2);


    }

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        threadTest.printChar();
        threadTest.start();
    }
}
