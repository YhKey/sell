package com.jluzh;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author by: hongkun.yang
 * @description: 测试类
 * @date: 2019/11/20 16:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    @Test
    public void test01(){
        String name="jz";
        String password="123";

        log.debug("debug...");
        log.info("info...name="+name+",password="+password);
        log.info("name:{},password:{}",name,password);
        log.error("error......");
    }
}
