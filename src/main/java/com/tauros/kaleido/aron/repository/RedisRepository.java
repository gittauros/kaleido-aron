package com.tauros.kaleido.aron.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

/**
 * @author zhy
 * @date 2019/5/14
 */
@Repository
public class RedisRepository {

    @Resource
    private RedisTemplate redisTemplate;

    public static class Sample implements Serializable {

        private static final long serialVersionUID = -3435492383848024694L;

        static class InstanceHolder {
            static Sample INSTANCE = new Sample();
        }

        private int x;
        private String str;

        public Sample() {
            this.x = new Random().nextInt();
            this.str = Integer.toHexString(new Integer(new Random().nextInt()).hashCode());
        }
    }

    public void putTestObj() throws IOException {
        redisTemplate.opsForValue().set("testObj", Sample.InstanceHolder.INSTANCE);
    }

    public Object getTestObject() {
        return redisTemplate.opsForValue().get("testObj");
    }
}
