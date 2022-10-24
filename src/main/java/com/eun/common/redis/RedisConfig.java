//package com.eun.common.redis;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.data.redis.connection.RedisServer;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import java.io.IOException;
//
//@Slf4j //lombok
//@Profile("local") // profile이 local일때만 활성화
//@Configuration
//public class RedisConfig {
//
//    private int redisPort = 6874;
//
//    private RedisServer redisServer;
//
//    @PostConstruct
//    public void redisServer() throws IOException {
//        redisServer = new RedisServer(redisPort);
//        redisServer.start();
//    }
//
//    @PreDestroy
//    public void stopRedis() {
//        if (redisServer != null) {
//            redisServer.stop();
//        }
//    }
//
//}
