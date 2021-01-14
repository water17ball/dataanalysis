package cn.enn.bigdata.dataanalysis.callback;

import java.util.function.Function;

public class FuncCallBack {

    static class Person {
        Person() {
        }

        ;


        String name;

        public void setName(String name) {
            this.name = name;
        }

        public void sayName(Function<Person, Boolean> function) {
            boolean result = function.apply(this);
        }
    }


    public static void main(String[] args) {
        Person xiaoming = new Person();
        xiaoming.setName("xiaoming");
//        xiaoming.sayName(new Function<Person, Boolean>() {
//            @Override
//            public Boolean apply(Person person) {
//                System.out.println(person.name + ": ni hao ya!");
//                return true;
//            }
//        });
        xiaoming.sayName(person -> {
            System.out.println(person.name + ": ni hao ya!");
            return true;
        });
    }
}
