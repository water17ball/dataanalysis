package cn.wp.bigdata.dataanalysis.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloServiceTest {

    @RountingInjected(value = "helloServiceImpl2")
    private HelloService helloService;

    public void testSayHello() {
        helloService.sayHello();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("cn.enn.bigdata.dataanalysis.aop");
        HelloServiceTest helloServiceTest = applicationContext.getBean(HelloServiceTest.class);
        helloServiceTest.testSayHello();
    }
}