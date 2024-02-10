package com.yeji.couponservice.application.port.out;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "redis.trylock")
public class RedisLockProperties {

    private Integer waitTime;

    private Integer leaseTime;
}
