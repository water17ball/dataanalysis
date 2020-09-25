package cn.wp.bigdata.dataanalysis.spi;

public class SpiTest {
    public static void main(String[] args) {
        Info infoInstance  = InfoFactory.getInstance();
        System.out.println(infoInstance.getInfo());

    }

}
