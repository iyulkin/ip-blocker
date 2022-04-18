package com.khakimova.ipblocker.config.property;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "ip.request")
public class IpRequestProperties {

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    @Positive
    private Long interval;
}
