package top.getidea;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.core.DebuggingClassWriter;
import sun.misc.ProxyGenerator;
import top.getidea.cglibProxy.Car;
import top.getidea.cglibProxy.proxy.CarCglibProxy;
import top.getidea.jdkProxy.Person;
import top.getidea.jdkProxy.imp.Student;
import top.getidea.jdkProxy.proxy.RetailerInvocationHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

@Slf4j
public class Main {
    public static void main(String[] args) {
        cglibProxy();
    }

    /**
     * 测试Cglib动态代理
     */
    private static void cglibProxy() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "src/main/");
        Car proxy = CarCglibProxy.createProxy(new Car());
        proxy.modify();
    }

    /**
     * 测试jdk动态代理
     */
    private static void jdkProxy() {
        saveProxyFile();
        Person student = new Student();
        RetailerInvocationHandler handler = new RetailerInvocationHandler(student);
        Person proxy = (Person) Proxy.newProxyInstance(student.getClass().getClassLoader(), student.getClass().getInterfaces(), handler);
        proxy.description();
    }

    /**
     * 保存动态生成的class文件。
     */
    private static void saveProxyFile() {
        FileOutputStream out = null;
        try {
            byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", Student.class.getInterfaces());
            out = new FileOutputStream("src/main/java/top/getidea/jdkProxy/imp/Student$Proxy0.class");
            out.write(classFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}