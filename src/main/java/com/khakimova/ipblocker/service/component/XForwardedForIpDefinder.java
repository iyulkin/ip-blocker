package com.khakimova.ipblocker.service.component;

import org.springframework.stereotype.Component;

@Component
public class XForwardedForIpDefinder implements IpDefinderByRequestHeader {
    @Override
    public Integer priority() {
        return 20;
    }

    @Override
    public String requestHeader() {
        return "X-FORWARDED-FOR";
    }
}
