package com.btjf.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

@Component
public class LoginInfoCache {

    private final static Cache<String, Object> cache = CacheBuilder.newBuilder()
            //设置cache的初始大小为10，要合理设置该值
            .initialCapacity(10)
            //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
            .concurrencyLevel(5)
            //设置cache中的数据在写入之后的存活时间为3600秒
            .expireAfterWrite(3600, TimeUnit.SECONDS)
            //构建cache实例
            .build();


    public void add(String key, Object value){
        cache.put(key,value);
    }

    public Object get(String key){
        return cache.getIfPresent(key);
    }

    public void delete(String key){
        //map.remove(key);
    }

}
