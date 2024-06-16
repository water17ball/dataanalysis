package cn.enn.bigdata.dataanalysis.innerclass;

public class Son extends Parent {

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    public int age;

    public Son(int age, String name) {
        super(age+18, name);
        this.age  = age;
    }

    public static void main(String[] args) {
        Son son = new Son(20, "jack");
        System.out.println(son.getAge());
        System.out.println(son.getName());
        System.out.println(son.age);

        /**
         * 匿名内部类
         */
        Son anonymitySon = new Son(30, "jack") {
            @Override
            public int getAge() {
                return  this.age + 30;
            }
        };
        System.out.println(anonymitySon.getAge());
    }

}
