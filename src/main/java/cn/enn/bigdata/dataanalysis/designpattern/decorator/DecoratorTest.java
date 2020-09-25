package cn.enn.bigdata.dataanalysis.designpattern.decorator;

public class DecoratorTest {
    public static void main(String[] args) {
        Beverage beverage = new Mocha(new Mocha(new Soy(new Mocha(new Expresso()))));
        System.out.println(beverage.getDesc());
        System.out.println(beverage.getCost()+0.00001);

    }
}
