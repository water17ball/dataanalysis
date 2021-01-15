package cn.enn.bigdata.dataanalysis.utils;

import cn.enn.bigdata.dataanalysis.entity.Bottle;
import cn.enn.bigdata.dataanalysis.entity.Cup;
import cn.enn.bigdata.dataanalysis.entity.Integer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;
import sun.applet.Main;

import java.io.FileOutputStream;
import java.sql.Time;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class SingletonA {
    private static SingletonA ourInstance = new SingletonA();

    public static SingletonA getInstance() {
        return ourInstance;
    }

    private SingletonA() {
    }

    public void print() {
        System.out.println("singletonA");
    }

    public static void main(String[] args) {
        SingletonA a = SingletonA.getInstance();
        a.print();


        ThreadFactory b = new CustomizableThreadFactory("pool-");
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), b, new ThreadPoolExecutor.AbortPolicy());
//        ReentrantLock lock = new ReentrantLock();
//        lock.lock();

        Bottle bottle = new Bottle();
        bottle.setName("plastic");
        bottle.setId(1);
        bottle.setCover(true);
        bottle.setCapacity(500);

        Cup cup = new Cup();
        cup.setName("china");
        cup.setId(2);
        cup.setCapacity(250);

        System.out.println(bottle);
        System.out.println(cup);

        try {
            BeanToolUtil.copyProperties(bottle, cup);
            System.out.println(cup);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Integer integer = new Integer();//自定义的与系统类重名的竟然也可以。。。包名不一样。
        integer.setValue(100);
        System.out.println(integer);

    }
}
