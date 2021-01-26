package cn.enn.bigdata.dataanalysis.designpattern.chainandstrategy;

public class RootRouterTest {
    public static void main(String[] args) {
        RootRouter rootRouter = new RootRouter();
        rootRouter.abstractInit();//手动注册一下。实际上这个方法在容器中可以在初始化之后执行。
        rootRouter.applyStrategy("01");
        rootRouter.applyStrategy("02");
        rootRouter.applyStrategy("03");
    }
}
