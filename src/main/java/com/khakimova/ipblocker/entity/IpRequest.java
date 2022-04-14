package com.khakimova.ipblocker.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("IpRequest")
public class IpRequest implements Serializable {
    @Id
    private String id;
    private List<Long> requests = new ArrayList<>();
}
