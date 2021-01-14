package cn.enn.bigdata.dataanalysis.callback;

public class InterfaceCallBack {

    interface Greeting {
        public void greet();
    }


    static class Person {
        Person() {
        }

        ;


        String name;

        public void setName(String name) {
            this.name = name;
        }

        public void sayName(Greeting greeting) {
            System.out.println(this.name);
            greeting.greet();
        }
    }


    public static void main(String[] args) {
        Person xiaoming = new Person();
        xiaoming.setName("xiaoming");
        xiaoming.sayName(new Greeting() {
            @Override
            public void greet() {
                System.out.println("ni hao ya!");
            }
        });

    }
}
