package com.khakimova.ipblocker.aop;

import com.khakimova.ipblocker.exception.NumberOfRequestsExceededException;
import com.khakimova.ipblocker.service.IpRequestService;
import com.khakimova.ipblocker.service.component.IpDefinder;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class IpBlockerAspect {

    private final IpRequestService service;
    private final IpDefinder ipDefinder;

    @Pointcut("@annotation(com.khakimova.ipblocker.aop.IpBlocker)")
    public void ipBlockerMethodCall() {
    }

    @Before("ipBlockerMethodCall()")
    public void blockByIp(JoinPoint jp) {
        HttpServletRequest request = getHttpServletRequest();

        String ip = ipDefinder.defineIp(request);
        log.debug("Request from {}", ip);
        if (!service.checkIpRequestsLessThanMaxQuantity(ip)) {
            // todo: maybe here we need to save user request too - clarify with client
            throw new NumberOfRequestsExceededException();
        }
        service.addRequest(ip);
    }

    private HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if(requestAttributes == null) {
            return null;
        } else {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
    }
}
