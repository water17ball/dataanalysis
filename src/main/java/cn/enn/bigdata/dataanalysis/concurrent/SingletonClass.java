package cn.enn.bigdata.dataanalysis.concurrent;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

@Component
public class SingletonClass {
    private String name;
    private static SingletonClass ourInstance = new SingletonClass();

    public static SingletonClass getInstance() {
        return ourInstance;
    }

    private SingletonClass() {
        name = "nihao";
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        SingletonClass a = SingletonClass.getInstance();
        System.out.println(a.getName());

        SqlSessionTemplate sqlSessionTemplate;
        Configuration configuration;
    }
}
