package cn.enn.bigdata.dataanalysis.aop;


import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl2 implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("你好我是HelloServiceImpl2");
    }
}