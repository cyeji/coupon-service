package com.yeji.couponservice.application.port.out;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "retry")
public class DatabaseLockProperties {

    private Integer count;

    private Integer sleep;
}
