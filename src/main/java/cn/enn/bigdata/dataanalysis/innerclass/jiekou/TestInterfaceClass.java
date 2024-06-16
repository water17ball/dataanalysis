package cn.enn.bigdata.dataanalysis.innerclass.jiekou;

public class TestInterfaceClass {
    public static void main(String[] args) {
        /**
         * 匿名内部类——实现接口
         */
        WorkService nimingClass = new WorkService() {
            @Override
            public String getName(String work) {
                return "Hello" + work;
            }

            @Override
            public String getHighName(String work) {
                return getName(work) + " High";
            }
        };
        System.out.println(nimingClass.getName(" world!"));
        System.out.println(nimingClass.getHighName(" world!"));
    }
}
