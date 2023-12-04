package com.example.billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class ConsulConfigRestController {
    @Autowired
    private MyConsulConfig myConsulConfig;
    @Autowired
    private MyVaultconfig myVaultconfig;
    private long accessTokenTimeout;
    private long refreshTokenTimeout;

//    @GetMapping("/MyConfig")
//    public MyConsulConfig MyConfig(){
//        return myConsulConfig;
//    }

    @GetMapping("/MyConfig")
    public Map<String , Object> MyConfig(){
        return Map.of("MyConsulConfig",myConsulConfig , "myVaultconfig",myVaultconfig);
    }
}
