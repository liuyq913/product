package com.btjf.interceptor;

import com.btjf.common.utils.JSONUtils;
import com.btjf.common.utils.MD5Utils;
import com.btjf.service.emp.EmpService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class LoginInfoCache implements ApplicationListener<ContextRefreshedEvent> {
    @Resource
    private EmpService empService;

    private static ConcurrentHashMap<String, Object> foreverCashe = new ConcurrentHashMap();

    private final static Cache<String, Object> cache = CacheBuilder.newBuilder()
            //设置cache的初始大小为10，要合理设置该值
            .initialCapacity(10)
            //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
            .concurrencyLevel(5)
            //设置cache中的数据在写入之后的存活时间为3600秒
            .expireAfterWrite(3600, TimeUnit.SECONDS)
            //构建cache实例
            .build();


    public void add(String key, Object value) {
        cache.put(key, value);
    }

    public Object get(String key) {
        return cache.getIfPresent(key);
    }

    public void delete(String key) {
        //map.remove(key);
    }

    public void foreverAdd(String key, Object value) {
        foreverCashe.put(key, value);
    }

    public Object getForever(String key) {
        return foreverCashe.get(key);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (foreverCashe.size() == 0) {
            empService.getAll().stream().forEach(t -> {
                String json = JSONUtils.toJSON(t);
                String key = MD5Utils.ecodeByMD5(json);
                foreverCashe.put(key, t);
            });
        }
    }
}
