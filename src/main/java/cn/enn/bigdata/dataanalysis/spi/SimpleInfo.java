package cn.enn.bigdata.dataanalysis.spi;

public class SimpleInfo implements Info {
    @Override
    public String getInfo() {
        Class a = String.class;
        try {
            a.getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        return "SimpleInfo ";
    }
}
