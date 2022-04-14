package com.khakimova.ipblocker.controller;

import com.khakimova.ipblocker.exception.NumberOfRequestsExceededException;
import com.khakimova.ipblocker.service.IpRequstService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private final IpRequstService service;

    @GetMapping("/")
    public void main(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");
        log.debug("Request from {}", ip);
        Boolean is200 = service.checkIpRequestsLessThanMaxQuantity(ip);
        service.addRequest(ip);
        if(!is200) {
            throw new NumberOfRequestsExceededException();
        }
    }
}
