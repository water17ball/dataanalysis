package cn.wp.bigdata.dataanalysis.concurrent;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AQSTest {
    private ReentrantLock reentrantLock;
    Queue<String> stringQueue;

    AQSTest() {
        reentrantLock = new ReentrantLock(false);
        stringQueue = new LinkedBlockingQueue<>(16);
    }

    class AqsModel extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            int state = getState();
            boolean b = compareAndSetState(state, arg + state);
            return b;
        }

        @Override
        protected boolean tryRelease(int arg) {
            int state = getState();
            boolean b = compareAndSetState(state, state - arg);
            return b;
        }

        /**
         * state包含value和version
         * |----Byte----|----Byte----|----Byte----|----Byte----|
         * |                 value                |    version |
         *
         * @param arg
         * @return
         */
        boolean compareAndSetStateWithVersion(int arg) {
            if (Math.abs(arg) > (2 << 23)) {//大于3个字节的数字就不支持了。
                return false;
            }

            int state = getState();
            int version = state & 0xff;
            version++;
            if (version > (2 << 7)) {
                version = 0;
            }
            int newState = (arg << 8) | version;
            if (arg < 0)//负值则加上符号
            {
                newState = newState | 0x80000000;
            }
            System.out.printf("%8x\n", newState);

            boolean b = compareAndSetState(state, newState);
            return b;
        }

    }

    public void produce() throws InterruptedException {
        for (; ; ) {

            int time = (int)(Math.random() * 2000);
            Thread.sleep(time);
            reentrantLock.lock();
            String s = new Date().toString();
            stringQueue.add(s);
            System.out.println("produce: " + s);
            reentrantLock.unlock();
        }
    }

    public void consume() {
        for (; ; ) {
            reentrantLock.lock();
            String s = stringQueue.poll();
            if (s == null) {
                reentrantLock.unlock();
                try {
                    System.out.println("consume: sleep 3s");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("consume: " + s);
                reentrantLock.unlock();
            }
        }
    }

    public void testAQS() {
        AqsModel aqsModel = new AqsModel();
        aqsModel.acquire(1);
        aqsModel.release(1);
        aqsModel.compareAndSetStateWithVersion(2);
        aqsModel.compareAndSetStateWithVersion(3);
        aqsModel.compareAndSetStateWithVersion(-1);

        ReentrantLock reentrantLock = new ReentrantLock(false);
        Condition condition = reentrantLock.newCondition();
        reentrantLock.lock();
        try {
            System.out.println("await -----");
            condition.await();
            System.out.println("await finished!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        Map<String, String> map = new HashMap<>();
    }

    class Node {
        public final static int CONDITION = -1;

        public int waitStatus;
        public Node next;
    }

    private Node firstWaiter;
    private Node tailWaiter;

    public void removeCancelNodes() {//unlinkCancelledWaiters
        if (firstWaiter == null) {
            return;
        }

        Node c = firstWaiter;
        Node n = c.next;
        Node prev = null;

        while (c != null) {
            if (c.waitStatus != Node.CONDITION) {
                if (c == firstWaiter) {
                    firstWaiter = n;
                    c = n;
                    if (n != null)
                        n = n.next;
                } else {
                    prev = n;
                    c = n.next;
                    if (n != null)
                        n = n.next;
                }
            } else {
                prev = c;
                c = n;
                if (n != null)
                    n = n.next;
            }

        }
    }

    public static void main(String[] args) {
        AQSTest aqsTest = new AQSTest();
        aqsTest.testAQS();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, Runtime.getRuntime().availableProcessors() * 2, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2));
        threadPoolExecutor.submit(() -> {
            try {
                aqsTest.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPoolExecutor.submit(() -> {
            aqsTest.consume();
        });


        for (; ; ) {
            try {
                Thread.sleep(6000);
                threadPoolExecutor.shutdown();
                if (!threadPoolExecutor.isTerminated()) {
                    threadPoolExecutor.shutdownNow();
                    Thread.sleep(1000);
                    System.out.println("is Not Terminated");

                    break;
                } else {
                    System.out.println("isTerminated");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
