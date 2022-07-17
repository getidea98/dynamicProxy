package top.getidea.cglibProxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Car {

    public void run() {
     log.info("车在高速跑");
    }

    public void modify() {
        log.info("车在4S店保养");
    }
}
