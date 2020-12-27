package cn.enn.bigdata.dataanalysis.controller;

import cn.enn.bigdata.dataanalysis.WebApplication;
import cn.enn.bigdata.dataanalysis.aop.HelloService;
import cn.enn.bigdata.dataanalysis.aop.HelloServiceImpl1;
import com.sun.xml.internal.ws.policy.spi.AssertionCreationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
//@RunWith是JUnit的一个注解, 用来告诉JUnit不要使用内置的方式进行单元测试, 而应该使用指定的类做单元测试 对于Spring单元测试总是要使用 SpringRunner.class .
@SpringBootTest(classes = WebApplication.class)
//@SpringBootTest 用来指定SpringBoot应用程序的入口类, 该注解默认会根据包名逐级往上找, 一直找到一个SpringBoot主程序class为止, 然后启动该类为单元测试准备Spring上下文环境.  Spring单元测试并不在每个测试方法前都移动一个全新的Spring上下文, 因为这样做太耗费时间, 而是会缓存上下文环境. 如果某个测试方法需要重新准备Spring上下文, 需要在该方法上加 @DirtiesContext 注解.
public class HelloTest {

    @Resource(name = "helloServiceImpl1")
    HelloService helloService;

    @Test//@Test注解: JUnit在执行每个测试方法之前, 都会为测试类创建一个新的实例, 这样有助于隔离各个测试方法之前的相互影响.
    public void testHello() {
        helloService.sayHello();
//        Assert.assertNull(helloService.sayHello());

    }

}
