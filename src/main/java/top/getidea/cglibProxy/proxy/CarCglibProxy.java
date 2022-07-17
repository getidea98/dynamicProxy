package top.getidea.cglibProxy.proxy;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理类
 */
@Slf4j
public class CarCglibProxy implements MethodInterceptor {
 
    /**
     * 目标对象
     */
    final private Object target;
 
    public CarCglibProxy(Object target) {
        this.target = target;
    }
 
    /**
     * 代理对象方法拦截器
     * @param o 代理对象
     * @param method 被代理的类的方法，
     * @param args 调用方法传递的参数
     * @param methodProxy 方法代理对象
     * @return 被调用方法的返回值
     * @throws Throwable 抛出异常
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("target:" + target.toString());
        log.info("before");
        Object result;
        try {
            result = methodProxy.invokeSuper(o, args); // 被代理的方法没有返回值
            log.info("after");
        } catch (Throwable throwable) {
            log.info("except");
            throw throwable;
        } finally {
            log.info("final");
        }
        return result;
    }
 
    public static <T> T createProxy(T target) {
        CarCglibProxy cglibProxyTest = new CarCglibProxy(target);
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(cglibProxyTest);
        enhancer.setSuperclass(target.getClass());
        return (T) enhancer.create();
    }
}