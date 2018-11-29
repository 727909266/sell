package com.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    @Test
    public void test1(){
        String name = "sell";
        String password = "123456";
        log.debug("debug: name: {}, password: {}", name, password);
        log.info("info: name: {}, password: {}", name, password);
        log.error("error: name: {}, password: {}", name, password);
    }
}
