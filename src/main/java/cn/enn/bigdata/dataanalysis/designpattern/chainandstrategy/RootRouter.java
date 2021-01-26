package cn.enn.bigdata.dataanalysis.designpattern.chainandstrategy;

public class RootRouter extends AbstractStrategyRouter {
    /**
     * 抽象方法，需要子类实现策略的分发逻辑
     *
     * @return 分发逻辑 Mapper 对象
     */
    @Override
    protected StrategyMapper registerStrategyMapper() {
        return new StrategyMapperImpl();
    }
}
