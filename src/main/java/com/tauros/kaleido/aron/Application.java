package com.tauros.kaleido.aron;

//import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by zhy on 2018/10/26.
 */

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableDiscoveryClient
@EnableFeignClients
//@EnableDubbo(scanBasePackages = "com.tauros.kaleido.aron")
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
