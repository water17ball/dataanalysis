package cn.enn.bigdata.dataanalysis.beanpostprocessor;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class AsyncBeanPostProcess implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //System.out.println("into AsyncBeanPostProcess: " + beanName);

        if (bean.getClass().getAnnotation(Data.class) != null) {//不起作用的！！因为Data注解的RetentionPolicy.SOURCE，只在源码阶段有效果
            System.out.println(beanName + " has Annotation @Data!---------------------");
        }

        if (bean.getClass().getAnnotation(Component.class) != null) {//起作用！注解的RetentionPolicy.RUNTIME,在运行阶段有效果
            //System.out.println(beanName + " has Annotation @Component!---------------------");
        }

        return bean;
    }
}
