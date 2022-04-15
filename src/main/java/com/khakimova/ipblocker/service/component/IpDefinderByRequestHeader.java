package com.khakimova.ipblocker.service.component;

import javax.servlet.http.HttpServletRequest;

public interface IpDefinderByRequestHeader {

    Integer priority();

    String requestHeader();

    default String defineIp(HttpServletRequest request) {
        String header = request.getHeader(requestHeader());
        if (!(header == null || header.isBlank() || "unknown".equalsIgnoreCase(header))) {
            return header.split(",")[0];
        }
        return null;
    }
}
