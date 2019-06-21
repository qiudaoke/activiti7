package com.shareniu.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author 分享牛
 */
@SpringBootApplication(exclude ={ SecurityAutoConfiguration.class} )
public class ShareniuApplicationV1 {

    public static void main(String[] args) {
        SpringApplication.run(ShareniuApplicationV1.class, args);
    }

}
