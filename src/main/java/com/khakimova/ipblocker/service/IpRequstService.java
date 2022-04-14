package com.khakimova.ipblocker.service;

import com.khakimova.ipblocker.config.property.IpRequestProperties;
import com.khakimova.ipblocker.entity.IpRequest;
import com.khakimova.ipblocker.repository.IpRequestRepository;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IpRequstService {
    private final IpRequestProperties properties;
    private final IpRequestRepository repository;

    public boolean checkIpRequestsLessThanMaxQuantity(String ip) {
        Long currentTimeInMillis = getCurrentTimeInMillis();

        return Optional.ofNullable(repository.findById(ip)).map(ipRequest -> ipRequest.getRequests().stream()
                .filter(time -> time >= currentTimeInMillis - properties.getInterval())
                .count() < properties.getQuantity()).orElse(false);
    }

    public void addRequest(String ip) {
        Long currentTimeInMillis = getCurrentTimeInMillis();

        Optional.ofNullable(repository.findById(ip)).ifPresentOrElse(ipRequest -> {
            ipRequest.getRequests().removeIf(time -> time < currentTimeInMillis - properties.getInterval());
            ipRequest.getRequests().add(currentTimeInMillis);
            repository.save(ipRequest);
        }, () -> {
            IpRequest request = new IpRequest();
            request.setId(ip);
            request.getRequests().add(currentTimeInMillis);
            repository.save(request);
        });
    }

    private Long getCurrentTimeInMillis() {
        return (new Date()).getTime();
    }
}
