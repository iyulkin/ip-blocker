package com.khakimova.ipblocker.service.component;

import org.springframework.stereotype.Component;

@Component
public class ProxyClientIpDefinder implements IpDefinderByRequestHeader {
    @Override
    public Integer priority() {
        return 30;
    }

    @Override
    public String requestHeader() {
        return "PROXY-CLIENT-IP";
    }
}
