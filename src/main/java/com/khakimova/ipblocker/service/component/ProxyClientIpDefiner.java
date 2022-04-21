package com.khakimova.ipblocker.service.component;

import org.springframework.stereotype.Component;

@Component
public class ProxyClientIpDefiner implements IpDefinerByRequestHeader {
    @Override
    public Integer priority() {
        return 30;
    }

    @Override
    public String requestHeader() {
        return "PROXY-CLIENT-IP";
    }
}
