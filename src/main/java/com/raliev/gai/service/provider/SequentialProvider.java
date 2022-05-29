package com.raliev.gai.service.provider;

import com.raliev.gai.repositories.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SequentialProvider implements RegNumberProvider {

    @Autowired
    private RedisRepository redisRepository;

    @Override
    public String provide() {
        return redisRepository.removeFirst();
    }
}
