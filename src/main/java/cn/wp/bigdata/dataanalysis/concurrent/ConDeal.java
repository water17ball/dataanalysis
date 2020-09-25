package cn.wp.bigdata.dataanalysis.concurrent;

import java.util.concurrent.CountDownLatch;

public class ConDeal {

    public void CountDownLatchOne() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("thread 1 finished!" + System.currentTimeMillis());
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1950);
                    System.out.println("thread 2 finished!" + System.currentTimeMillis());
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            countDownLatch.await();
            System.out.println("子线程结束," + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void forwhile() {
        for (; ; ) {

        }

    }

    public void forwhile2() {

        while (true) {

        }
    }

    public static void main(String[] args) {
        ConDeal conDeal = new ConDeal();
        conDeal.CountDownLatchOne();
    }
}
