package com.khakimova.ipblocker.repository;

import com.khakimova.ipblocker.entity.IpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class IpRequestRepository {
    private static final String HASH_KEY = "IpRequest";
//    private final RedisTemplate template;

    public void save(IpRequest request) {
//        template.opsForHash().put(HASH_KEY, request.getId(), request);
        log.debug("Saving {}", request);
    }

    public IpRequest findById(String id) {
//        return (IpRequest) template.opsForHash().get(HASH_KEY, id);
        log.debug("Finding request by ip {}", id);
        return null;
    }
}
