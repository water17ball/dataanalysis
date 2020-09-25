package cn.wp.bigdata.dataanalysis.aop;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl1 implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("你好我是HelloServiceImpl1");
    }
}