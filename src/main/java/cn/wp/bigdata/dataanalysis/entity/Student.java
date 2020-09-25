package cn.wp.bigdata.dataanalysis.entity;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Scope(value = "prototype")//默认是singleton。还可以设置为prototype原型模式，每次根据原型生成一个新的
@Component
@Data
public class Student {
    static AtomicInteger curIndex = new AtomicInteger();
    final int id = getInitId();

    private Integer getInitId() {
        return curIndex.getAndIncrement();
    }

    String name;
    int age;


}
