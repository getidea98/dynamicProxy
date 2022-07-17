package top.getidea.jdkProxy.imp;

import lombok.extern.slf4j.Slf4j;
import top.getidea.jdkProxy.Person;

@Slf4j
public class Student implements Person {

    @Override
    public void description() {
        log.info("我是学生，正在学校上课");
    }
}
