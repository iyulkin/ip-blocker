package com.khakimova.ipblocker.repository;

import com.khakimova.ipblocker.entity.IpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IpRequestRepository {
    private static final String HASH_KEY = "IpRequest";
    private final RedisTemplate template;

    public void save(IpRequest request) {
        template.opsForHash().put(HASH_KEY, request.getId(), request);
    }

    public IpRequest findById(String id) {
        return (IpRequest) template.opsForHash().get(HASH_KEY, id);
    }
}
