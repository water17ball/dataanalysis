package cn.wp.bigdata.dataanalysis.designpattern.decorator;

public class Mocha extends CondimentDecorator {
    Beverage beverage;

    Mocha(Beverage beverage){
        this.beverage = beverage;
    }
    @Override
    public String getDesc() {
        return beverage.getDesc() + ",Mocha";
    }

    @Override
    public double getCost() {
        return .22 + beverage.getCost();
    }
}
