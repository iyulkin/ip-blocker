package com.khakimova.ipblocker.config.property;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {

    @NotBlank
    private String host;

    @NotNull
    @Positive
    private Integer port;
}
