package com.khakimova.ipblocker.service;

import com.khakimova.ipblocker.config.property.IpRequestProperties;
import com.khakimova.ipblocker.entity.IpRequest;
import com.khakimova.ipblocker.repository.IpRequestRepository;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IpRequestService {
    private final IpRequestProperties properties;
    private final IpRequestRepository repository;

    public boolean checkIpRequestsLessThanMaxQuantity(String ip) {
        if(ip == null) {
            return false;
        }

        Long currentTimeInMillis = getCurrentTimeInMillis();

        return Optional.ofNullable(repository.findById(ip)).map(ipRequest ->
                ipRequest.getRequests().stream()
                        .filter(time -> time >= currentTimeInMillis - properties.getInterval())
                        .count() < properties.getQuantity()).orElse(true);
    }

    public void addRequest(String ip) {
        Long currentTimeInMillis = getCurrentTimeInMillis();

        Optional.ofNullable(repository.findById(ip)).ifPresentOrElse(ipRequest -> {
            ipRequest.getRequests().removeIf(time -> time < currentTimeInMillis - properties.getInterval());
            ipRequest.getRequests().add(currentTimeInMillis);
            repository.save(ipRequest);
            log.debug("Requests by ip {} updated {}", ip, ipRequest.getRequests());
        }, () -> {
            IpRequest ipRequest = new IpRequest();
            ipRequest.setId(ip);
            ipRequest.getRequests().add(currentTimeInMillis);
            repository.save(ipRequest);
            log.debug("Requests by ip {} created {}", ip, ipRequest.getRequests());
        });
    }

    private Long getCurrentTimeInMillis() {
        return (new Date()).getTime();
    }
}
