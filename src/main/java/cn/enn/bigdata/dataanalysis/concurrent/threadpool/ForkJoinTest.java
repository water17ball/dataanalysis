package cn.enn.bigdata.dataanalysis.concurrent.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ForkJoinTest {
    public static ForkJoinPool forkJoinPool = new ForkJoinPool(4);
    public static ForkJoinPool forkJoinPool2 = ForkJoinPool.commonPool();//CompletableFuture和Parallel Streams，也是用这个公用的并行线程池 为了避免任何并行操作都引入一个线程池，最坏情况会导致在单个JVM上创建了太多的池线程，降低效率。
    static class Task extends RecursiveTask<Integer> {
        private int start;
        private int end;
        Task(Integer s, Integer e){
            start = s;
            end = e;
        }


        @Override
        protected Integer compute() {
            if(end-start > 10){
                System.out.println(start);
                System.out.println(end);
                int middle = (end + start)/2;
                Task task1 = new Task(start, middle-1);

                Task task2 = new Task(middle, end);
                task1.fork();
                task2.fork();

                return task1.join() + task2.join();
            }
            else{
                return IntStream.rangeClosed(start, end).sum();
//                int sum = 0;
//                for (int i = start; i <=end ; i++) {
//                    sum += i;
//                }
//                return sum;
            }

        }
    }

    public static void main(String[] args) {
        Task task = new Task(1, 100000);
        ForkJoinTask<Integer> submit = forkJoinPool.submit(task);
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(Stream.generate(Math::random).limit(10).parallel().reduce((a,b)->a+b));
//        try {
//            System.out.println(task.fork().get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }
}
