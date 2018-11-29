package com.sell.common;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.cache.CacheProperties;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Study {
    //~----cache define-----
    private Cache<String, String> cpSongId2MusicInfo = Caffeine.newBuilder()
            .maximumSize(3500)
            .expireAfterWrite(2, TimeUnit.MINUTES)
            .removalListener((k, v, cause) -> log.debug("remove cause {}", cause))
            .build();

    private Executor executor = new ThreadPoolExecutor(Environment.CPU_AMOUNT * 2,
            Environment.CPU_AMOUNT * 5,
            1,
            TimeUnit.MINUTES,
            new ArrayBlockingQueue(100),
            new ThreadPoolExecutor.CallerRunsPolicy());

}
