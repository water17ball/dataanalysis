package cn.wp.bigdata.dataanalysis.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取Context的类，实现ApplicationContextAware接口
 */
@Component
public class CurrentContext implements ApplicationContextAware {
    private static ApplicationContext curContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        curContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return curContext;
    }

    public static Object getBean(String beanName) {
        System.out.println("get bean:" + beanName);
        return curContext.getBean(beanName);
    }

}
