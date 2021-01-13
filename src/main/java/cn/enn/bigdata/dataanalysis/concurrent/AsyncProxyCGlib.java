package cn.enn.bigdata.dataanalysis.concurrent;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * @author wp
 * @since 2021.01.01
 * inspiration from @EnableAsync
 * 之前想着异步执行方法如何将参数传给调用方法呢？现在使用代理方式，正常调用方法传参，执行过程在代理中已经实现异步线程池调用。
 */
//异步代理的类，将被代理类的方法进行异步多线程执行
//其中的intercept方法，使用org.springframework.aop.interceptor.AsyncExecutionInterceptor::invoke方法
public class AsyncProxyCGlib implements MethodInterceptor {

    private ThreadPoolExecutor threadPoolExecutor = null;

    AsyncProxyCGlib() {
        threadPoolExecutor = new ThreadPoolExecutor(5, 10, 100, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10));
    }

    public Object getProxy(Class cls) {
        //CGLIB增强类对象
        Enhancer enhancer = new Enhancer();
        //设置增强类型
        enhancer.setSuperclass(cls);//CGLIB是通过继承所代理的类来完成动态代理的。如果类中有final方法，是不能执行的
        //定义代理逻辑对象为当前对象，要求当前对象实现MethodInterceptor方法
        enhancer.setCallback(this);
        //生成并返回代理对象
        return enhancer.create();
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        Callable<Object> task = () -> {
            try {
                Object result = methodProxy.invokeSuper(obj, args);
                if (result instanceof Future) {
                    return ((Future<?>) result).get();
                }
            }
//            catch (ExecutionException ex) {
//                handleError(ex.getCause(), userDeclaredMethod, invocation.getArguments());
//            }
//            catch (Throwable ex) {
//                handleError(ex, userDeclaredMethod, invocation.getArguments());
//            }
            catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

            return null;
        };

        threadPoolExecutor.submit(task);


        return null;
    }
}
