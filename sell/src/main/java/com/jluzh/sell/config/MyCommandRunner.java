package com.jluzh.sell.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author: yanghongkun
 * @description: 默认启动配置
 * @date: 2020/02/19
 */
@Component
@Slf4j
public class MyCommandRunner  implements CommandLineRunner {

    private boolean isOpen =true;

    @Override
    public void run(String... args) throws Exception {
        if(isOpen){
            String openUrl="/seller/login";
            String cmd="cmd /c start http://localhost:9999/sell"+openUrl;
            Runtime run = Runtime.getRuntime();
            try{
                run.exec(cmd);
                log.debug("启动浏览器打开项目成功");
            }catch (Exception e){
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
    }
}
