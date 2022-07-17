package top.getidea.jdkProxy.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//ʵ��InvocationHandler�ӿڵ�ʵ����
@Slf4j
public class RetailerInvocationHandler implements InvocationHandler {
    //����Ŀ�������
    /**
     * ������Ŀ��������������һ����Object��,�������������͵�
     */
    final private Object target;
    //�������췽��
 
    public RetailerInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("before" + Thread.currentThread());
        Object o;
        try {
            o = method.invoke(target,args); // ������ķ���û�з���ֵ
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