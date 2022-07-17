package top.getidea.jdkProxy.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//实现InvocationHandler接口的实现类
@Slf4j
public class RetailerInvocationHandler implements InvocationHandler {
    //创建目标类对象
    /**
     * 创建的目标对象的数据类型一定是Object的,不能是其他类型的
     */
    final private Object target;
    //创建构造方法
 
    public RetailerInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("before" + Thread.currentThread());
        Object o;
        try {
            o = method.invoke(target,args); // 被代理的方法没有返回值
            log.info("after" + Thread.currentThread());
        } catch (Throwable throwable) {
            log.info("except" + Thread.currentThread());
            throw throwable;
        } finally {
            log.info("final" + Thread.currentThread());
        }
        return o;
    }
}