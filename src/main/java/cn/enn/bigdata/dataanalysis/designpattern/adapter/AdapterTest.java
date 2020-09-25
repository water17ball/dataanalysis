package cn.enn.bigdata.dataanalysis.designpattern.adapter;

public class AdapterTest {
    public static void main(String[] args) {

    VoltageAdapter voltageAdapter = new VoltageAdapter();
    Voltage220V voltage220V  = new Voltage220V();
    voltageAdapter.output5V();
    }
}
