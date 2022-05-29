package com.raliev.gai.repositories;

import com.raliev.gai.service.initializer.DataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Optional;

@Repository
public class RedisRepositoryImpl implements RedisRepository {

    private static final String KEY = "regNumbers";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DataInitializer dataInitializer;

    private ListOperations<String, String> listOperations;

    @PostConstruct
    private void init() {
        listOperations = redisTemplate.opsForList();

        if (!Boolean.TRUE.equals(redisTemplate.hasKey(KEY))) {
            dataInitializer.init(this::addAll);
        }
    }

    @Override
    public String removeFirst() {
        return Optional.ofNullable(listOperations.leftPop(KEY))
                .orElseThrow(NoElementException::new);
    }

    @Override
    public String getByIndex(int index) {
        return Optional.ofNullable(listOperations.index(KEY, index))
                .orElseThrow(NoElementException::new);
    }

    @Override
    public void remove(String value) {
        listOperations.remove(KEY, 1, value);
    }

    @Override
    public void addAll(Collection<String> values) {
        listOperations.rightPushAll(KEY, values);
    }

    @Override
    public Long size() {
        return listOperations.size(KEY);
    }
}
