package cn.enn.bigdata.dataanalysis.async;

import java.util.concurrent.*;

public class FutureTest {
    public Future<String> dothing(Long sleepTimeMil){

        Callable<String> stringCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(sleepTimeMil);
                return "hello!";
            }
        };
        Future<String> stringFuture = Executors.newSingleThreadExecutor().submit(stringCallable);
        return stringFuture;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTest futureTest = new FutureTest();
        int sleepSecond = 10;
        Future<String> stringFuture = futureTest.dothing(sleepSecond * 1000L);
        System.out.println("check done:");
        for (int i = 0; i <= sleepSecond; i++) {
            System.out.println(stringFuture.isDone());
            Thread.sleep(1000);
        }
        System.out.println(stringFuture.get());

    }


}
