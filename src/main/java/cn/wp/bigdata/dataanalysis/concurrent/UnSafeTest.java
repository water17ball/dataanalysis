package cn.wp.bigdata.dataanalysis.concurrent;

import lombok.Data;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.PriorityQueue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class UnSafeTest {
    public static Unsafe unsafe;

    private int state = 0;
    private long head;
    private long tail;

    private static long stateOffset;
    private static long headOffset;
    private static long tailOffset;

    static {
        try {
            //Unsafe unsafe = Unsafe.getUnsafe();  //编译器禁止直接使用Unsafe

            Field unsafeField = Unsafe.class.getDeclaredFields()[0];
            unsafeField.setAccessible(true);
            unsafe = (Unsafe) unsafeField.get(null);

            stateOffset = unsafe.objectFieldOffset(UnSafeTest.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset(UnSafeTest.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset(UnSafeTest.class.getDeclaredField("tail"));
            System.out.println(stateOffset);
            System.out.println(headOffset);
            System.out.println(tailOffset);

            Field f = UnSafeTest.class.getDeclaredField("state");
            long offset = unsafe.objectFieldOffset(f);
            System.out.println(offset);

            boolean success = unsafe.compareAndSwapInt(f, offset, 0, 1);
            System.out.println(success);
            System.out.println(f.getName());

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void compareAndSetHead(long expectValue, long updateValue) {
        unsafe.compareAndSwapLong(this, headOffset, expectValue, updateValue);
    }

    public boolean compareAndSetState(int expectValue, int updateValue) {
        return unsafe.compareAndSwapLong(this, stateOffset, expectValue, updateValue);
    }


    public static void main(String[] args) {
        UnSafeTest unSafeTest = new UnSafeTest();
        System.out.println(unSafeTest.toString());
        boolean b = unSafeTest.compareAndSetState(0, 1);
        System.out.println(b);
        System.out.println(unSafeTest.toString());


        AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext();

        CountDownLatch countDownLatch = new CountDownLatch(2);


        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
//        cyclicBarrier.await();

        Semaphore semaphore = new Semaphore(2);

        Exchanger<String> stringExchanger = new Exchanger<>();
        try {
            semaphore.acquire();
            semaphore.release();
//            stringExchanger.exchange("nihao");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("exchange finished!");

        PriorityQueue<Runnable> runnables = new PriorityQueue<>();

//    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,300, TimeUnit.SECONDS,new LinkedBlockingQueue<>(10),ThreadFactoryBuilder.create().setNamePrefix("task-stock-").build());
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 300, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), TaskThreadFactory.getInstance().setThreadPrefixName("task-pool").build(), new ThreadPoolExecutor.DiscardOldestPolicy());

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Callable<Integer> integerCallable = new Callable<Integer>() {
            AtomicInteger count = new AtomicInteger();

            @Override
            public Integer call() throws Exception {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        };

        FutureTask<Integer> task = new FutureTask<>(integerCallable);


        for (int i = 0; i < 6; i++) {
            System.out.println(i);
//            threadPoolExecutor.execute(runnable);
//            executorService.execute(runnable);
            Future<Integer> submit = threadPoolExecutor.submit(integerCallable);
            try {
                Integer integer = submit.get();
                System.out.println(integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            threadPoolExecutor.submit(task);
        }
        threadPoolExecutor.shutdown();


    }

}
