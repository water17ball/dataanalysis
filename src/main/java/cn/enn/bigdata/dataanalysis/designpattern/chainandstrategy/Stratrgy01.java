package cn.enn.bigdata.dataanalysis.designpattern.chainandstrategy;

public class Stratrgy01 implements StrategyHandler {
    /**
     * apply strategy
     *
     * @param param
     * @return
     */
    @Override
    public Object apply(Object param) {
        System.out.println(this.getClass().getName() + " : " + param.toString());
        return null;
    }
}
