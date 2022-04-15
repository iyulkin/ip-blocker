package com.khakimova.ipblocker.controller;

import com.khakimova.ipblocker.exception.NumberOfRequestsExceededException;
import com.khakimova.ipblocker.service.IpRequestService;
import com.khakimova.ipblocker.aop.IpBlocker;
import com.khakimova.ipblocker.service.component.IpDefinder;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private final IpRequestService service;
    private final IpDefinder definder;

    @GetMapping("/")
    @IpBlocker
    public void main(HttpServletRequest request) {
        String ip = definder.defineIp(request);
        log.debug("Request from {}", ip);
        if (!service.checkIpRequestsLessThanMaxQuantity(ip)) {
            throw new NumberOfRequestsExceededException();
        }
        service.addRequest(ip);
    }
}
