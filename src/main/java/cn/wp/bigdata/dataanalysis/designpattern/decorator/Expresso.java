package cn.wp.bigdata.dataanalysis.designpattern.decorator;

public class Expresso extends Beverage {
    Expresso(){
        desc = "Expresso";
    }
    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public double getCost() {
        return 0.99;
    }
}
