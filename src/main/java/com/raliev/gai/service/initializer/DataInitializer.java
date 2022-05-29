package com.raliev.gai.service.initializer;

import java.util.List;
import java.util.function.Consumer;

public interface DataInitializer {

    void init(Consumer<List<String>> consumer);
}
