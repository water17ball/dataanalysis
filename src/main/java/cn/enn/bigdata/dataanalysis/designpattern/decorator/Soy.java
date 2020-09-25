package cn.enn.bigdata.dataanalysis.designpattern.decorator;

public class Soy extends CondimentDecorator {
    Beverage beverage;

    Soy(Beverage beverage){
       this.beverage = beverage;
    }

    @Override
    public String getDesc() {
        return beverage.getDesc() + ", Soy";
    }

    @Override
    public double getCost() {
        return 0.18 + beverage.getCost();
    }
}
