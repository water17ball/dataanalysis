package cn.enn.bigdata.dataanalysis.designpattern.singleton;

public class Singleton1 {
    private static Singleton1 instance = new Singleton1();

    public static Singleton1 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(Singleton1.getInstance());
        System.out.println(Singleton1.getInstance());
    }
}
