package com.khakimova.ipblocker.service.component;

import org.springframework.stereotype.Component;

@Component
public class XRealIpDefinder implements IpDefinderByRequestHeader {

    @Override
    public Integer priority() {
        return 10;
    }

    @Override
    public String requestHeader() {
        return "X-REAL-IP";
    }
}
