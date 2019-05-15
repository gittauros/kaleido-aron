package com.tauros.kaleido.aron.repository;

import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 * @author zhy
 * @date 2019/5/14
 */
@Repository
public class RedisRepository {

    @Resource
    private RedisTemplate redisTemplate;

    @Data
    public static class Sample implements Serializable {

        private static final long serialVersionUID = -3435492383848024694L;

        static class InstanceHolder {
            static Sample INSTANCE = new Sample();
        }

        private long x;
        private Long y;
        private String str;
        private String name;
        private Date date;

        public Sample() {
            this.x = new Random().nextInt();
            this.str = Integer.toHexString(new Integer(new Random().nextInt()).hashCode());
            this.name = "张黄羿" + this.str;
            this.date = new Date();
            this.y = new Long(new Random().nextInt());
        }
    }

    public void putTestObj() throws IOException {
        redisTemplate.opsForValue().set("testObj", Sample.InstanceHolder.INSTANCE);
    }

    public Object getTestObject() {
        return redisTemplate.opsForValue().get("testObj");
    }
}
