package cn.wp.bigdata.dataanalysis.service.impl;

import cn.wp.bigdata.dataanalysis.entity.ClassMethod;
import cn.wp.bigdata.dataanalysis.service.ReflectService;
import cn.wp.bigdata.dataanalysis.utils.CurrentContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Service
public class ReflectServiceImpl implements ReflectService {
    @Override
    public String runClassMethod(ClassMethod classMethod) {
        Object bean = CurrentContext.getBean(classMethod.getClassName());
        Class<?> beanClass = bean.getClass();
        try {
            Method method = beanClass.getMethod(classMethod.getMethodName(), null);
            Object invokeResult = method.invoke(bean, null);
            System.out.println(invokeResult.getClass().getName());
            System.out.println(invokeResult);
            return invokeResult.toString();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return null;
    }
}
