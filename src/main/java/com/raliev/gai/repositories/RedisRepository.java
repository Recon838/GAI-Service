package com.raliev.gai.repositories;

import java.util.Collection;

public interface RedisRepository {

    String removeFirst();

    String getByIndex(int index);

    void remove(String value);

    void addAll(Collection<String> values);

    Long size();
}
