package com.jiaxiang.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

@Component
public class ReactiveRedisUtils {

    @Autowired
    private ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;

    public Mono<Boolean> expire(String key, Duration timeout) {
        return reactiveRedisTemplate.expire(key, timeout);
    }

    public Mono<Long> getExpire(String key) {
        return reactiveRedisTemplate.getExpire(key).map(Duration::getSeconds);
    }

    public Mono<Boolean> hasKey(String key) {
        return reactiveRedisTemplate.hasKey(key);
    }

    public Mono<Long> del(String key) {
        return reactiveRedisTemplate.delete(key);
    }

    public <T> Mono<T> get(String key, Class<T> clazz) {
        return reactiveRedisTemplate.opsForValue().get(key)
                .cast(clazz);
    }

    public Mono<Boolean> set(String key, Object value) {
        return reactiveRedisTemplate.opsForValue().set(key, value);
    }

    public Mono<Boolean> set(String key, Object value, Duration timeout) {
        return reactiveRedisTemplate.opsForValue().set(key, value, timeout);
    }

    public <T> Mono<T> hGet(String key, String item, Class<T> clazz) {
        return reactiveRedisTemplate.opsForHash().get(key, item)
                .cast(clazz);
    }

    public Mono<Map<Object, Object>> hGetAll(String key) {
        return reactiveRedisTemplate.opsForHash().entries(key)
                .collectMap(Map.Entry::getKey, Map.Entry::getValue);
    }

    public Mono<Boolean> hSet(String key, String item, Object value) {
        return reactiveRedisTemplate.opsForHash().put(key, item, value);
    }

    public Mono<Boolean> hSetAll(String key, Map<String, Object> map) {
        return reactiveRedisTemplate.opsForHash().putAll(key, map);
    }

    public Mono<Long> hDel(String key, Object... item) {
        return reactiveRedisTemplate.opsForHash().remove(key, item);
    }

    public <T> Flux<T> lRange(String key, long start, long end, Class<T> clazz) {
        return reactiveRedisTemplate.opsForList().range(key, start, end)
                .cast(clazz);
    }

    public Mono<Long> lRightPush(String key, Object value) {
        return reactiveRedisTemplate.opsForList().rightPush(key, value);
    }

    public Mono<Object> lRightPop(String key) {
        return reactiveRedisTemplate.opsForList().rightPop(key);
    }
}