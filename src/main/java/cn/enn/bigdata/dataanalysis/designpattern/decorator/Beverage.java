package cn.enn.bigdata.dataanalysis.designpattern.decorator;

public abstract class Beverage {
    String desc = "Unknown Beverage";

    public abstract String getDesc();
    public abstract double getCost();
}
