package top.getidea.cglibProxy.proxy;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib��̬������
 */
@Slf4j
public class CarCglibProxy implements MethodInterceptor {
 
    /**
     * Ŀ�����
     */
    final private Object target;
 
    public CarCglibProxy(Object target) {
        this.target = target;
    }
 
    /**
     * ������󷽷�������
     * @param o �������
     * @param method ���������ķ�����
     * @param args ���÷������ݵĲ���
     * @param methodProxy �����������
     * @return �����÷����ķ���ֵ
     * @throws Throwable �׳��쳣
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("target:" + target.toString());
        log.info("before");
        Object result;
        try {
            result = methodProxy.invokeSuper(o, args); // ������ķ���û�з���ֵ
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