package cn.wp.bigdata.dataanalysis.designpattern.adapter;

public class VoltageAdapter extends Voltage220V implements IVoltage5V {

    @Override
    public int output5V() {
        return this.output220V()/44;
    }
}
