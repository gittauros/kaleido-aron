package com.tauros.kaleido.aron.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author zhy
 * @date 2019/5/14
 */
@Repository
public class RedisRepository {

    @Resource
    private RedisTemplate redisTemplate;
}
