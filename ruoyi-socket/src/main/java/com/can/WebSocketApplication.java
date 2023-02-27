package com.can;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @filename: com.can.WebSocketApplication
 * @author: HXJ
 * @date: 2023/2/27 7:16 PM
 * @version: V1.0
 * @description:
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class WebSocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class,args);
    }
}
