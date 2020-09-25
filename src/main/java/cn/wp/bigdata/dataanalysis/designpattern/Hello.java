package cn.wp.bigdata.dataanalysis.designpattern;

public class Hello {
    private static Hello ourInstance = new Hello();

    public static Hello getInstance() {
        return ourInstance;
    }

    private Hello() {
    }

    public void sayHello(){
        System.out.println("Hello ");
    }
}
