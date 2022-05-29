package com.raliev.gai.service.provider;

import com.raliev.gai.repositories.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomProvider implements RegNumberProvider {

    @Autowired
    private Random random;

    @Autowired
    private RedisRepository redisRepository;

    @Override
    public String provide() {
        int size = redisRepository.size().intValue(); // in this case it is safe cast
        int randomIndex = random.nextInt(size);
        String randomValue = redisRepository.getByIndex(randomIndex);
        redisRepository.remove(randomValue);
        return randomValue;
    }
}
