package cn.enn.bigdata.dataanalysis.designpattern.respchain;

/**
 * 责任链模式
 * 1.共同的任务处理接口
 * 2.添加到责任链中
 */
public class RespChain {

    public static void main(String[] args) {
        TenDeal tenDeal = new TenDeal();
        HundredDeal hundredDeal = new HundredDeal();
        tenDeal.setNext(hundredDeal);

        tenDeal.doTask(new Task("1", 1));
        tenDeal.doTask(new Task("11", 11));
        tenDeal.doTask(new Task("2", 2));
        tenDeal.doTask(new Task("101", 101));
    }

}
