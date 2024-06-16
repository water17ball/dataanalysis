package cn.enn.bigdata.dataanalysis.designpattern.singleton;

public class Singleton3 {
    private int a = 0;

    private Singleton3() {}
    // 主要是使用了 嵌套类可以访问外部类的静态属性和静态方法 的特性
    private static class Holder {
        private static Singleton3 instance = new Singleton3();
    }
    public static Singleton3 getInstance() {
        return Holder.instance;
    }

    public static void main(String[] args) {

        Singleton3 instance1 = Singleton3.getInstance();
        System.out.println(instance1);
        Singleton3 instance2 = Singleton3.getInstance();
        System.out.println(instance2);
    }
}
