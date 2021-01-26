package cn.enn.bigdata.dataanalysis.designpattern.chainandstrategy;

public class StrategyMapperImpl implements AbstractStrategyRouter.StrategyMapper {
    /**
     * 根据入参获取到对应的策略处理者。可通过 if-else 实现，也可通过 Map 实现。
     *
     * @param param 入参
     * @return 策略处理者
     */
    @Override
    public StrategyHandler get(Object param) {
        if ("01".equals(param.toString())) {
            return new Stratrgy01();
        } else if ("02".equals(param.toString())) {
            return new Stratrgy02();
        } else {
            return null;
        }
    }
}
