package com.khakimova.ipblocker.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ip.request")
public class IpRequestProperties {
    private Integer quantity;
    private Long interval;
}
