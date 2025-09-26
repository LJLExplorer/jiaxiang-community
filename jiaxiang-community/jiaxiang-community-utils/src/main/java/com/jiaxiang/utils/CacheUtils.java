package com.jiaxiang.utils;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Function;

@Slf4j
@Component
public class CacheUtils {

    private final RedisUtils redisUtils;

    private final AsyncTaskExecutor asyncTaskExecutor;

    public CacheUtils(RedisUtils redisUtils, AsyncTaskExecutor asyncTaskExecutor) {
        this.redisUtils = redisUtils;
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    /**
     * 查分页，从缓存得到分页数据
     *
     * @param pageNum       开始位置
     * @param pageSize      页大小
     * @param cacheKey      缓存key
     * @param ttL           过期时间
     * @param timeUnit      过期时间单位
     * @param tClass        T的类型
     * @param queryFunction 查询数据库的函数
     * @param <T>
     * @return 返回分页数据
     */
    public <T> List<T> getCacheByPage(
            int pageNum, int pageSize, String cacheKey, Long ttL, TimeUnit timeUnit,
            Class<T> tClass, BiFunction<Integer, Integer, List<T>> queryFunction) {
        List<T> cacheData = JSONUtil.toList((String) redisUtils.get(cacheKey), tClass);
        if (!cacheData.isEmpty()) {
            return cacheData;
        }
        List<T> tData = queryFunction.apply(pageNum, pageSize);
        if (tData.isEmpty()) {
            return null;
        }
        String jsonStr = JSONUtil.toJsonStr(tData);
        redisUtils.set(cacheKey, jsonStr, ttL, timeUnit);
        return tData;
    }

    /**
     * 查数据，缓存有在缓存查，没有存进缓存再查
     *
     * @param id
     * @param keyPrefix
     * @param tClass
     * @param queryFunction
     * @param ttL
     * @param timeUnit
     * @param <T>
     * @param <ID>
     * @return
     */
    public <T, ID> T getCache(ID id, String keyPrefix, Class<T> tClass,
                              Function<ID, T> queryFunction, Long ttL, TimeUnit timeUnit) {
        String redisStr = (String) redisUtils.get(keyPrefix + id);
        if (redisStr != null && !redisStr.isBlank()) {
            return JSONUtil.toBean(redisStr, tClass);
        }
        T t = queryFunction.apply(id);
        if (t == null) {
            return null;
        }
        String jsonStr = JSONUtil.toJsonStr(t);
        redisUtils.set(keyPrefix + id, jsonStr, ttL, timeUnit);
        return t;
    }

    /**
     * 异步删除整个缓存页
     */
    public void deleteCachePage(String prefix) {
        asyncTaskExecutor.runAsync(() -> {
//            log.info("异步开始时间：{}", System.currentTimeMillis());
            redisUtils.deleteKeysByPrefix(prefix);
//            log.info("异步结束时间：{}", System.currentTimeMillis());
        });
    }

    /**
     * 延时删除（实现延迟双删 保持一致数据库与redis一致性）
     *
     * @param prefix
     */
    public void delayDeleteCachePage(String prefix) {
        asyncTaskExecutor.runAsync(() -> {
            try {
//                log.info("延时异步开始时间：{}", System.currentTimeMillis());
                Thread.sleep(3000);
                redisUtils.deleteKeysByPrefix(prefix);
//                log.info("延时异步结束时间：{}", System.currentTimeMillis());
            } catch (Exception e) {
                log.info("延时删除出现异常");
            }
        });
    }
}
