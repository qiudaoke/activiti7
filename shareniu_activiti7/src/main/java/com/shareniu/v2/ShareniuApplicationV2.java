package com.shareniu.v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 分享牛
 */
@SpringBootApplication()

public class ShareniuApplicationV2 {

    public static void main(String[] args) {
        SpringApplication.run(ShareniuApplicationV2.class, args);
    }

}
