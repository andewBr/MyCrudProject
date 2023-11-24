package org.example;

import java.util.HashMap;
import java.util.Map;

class ApplicationContext {
    private Map<String, Object> beans = new HashMap<>();

    public void registerBean(String name, Object bean) {
        beans.put(name, bean);
    }

    public Object getBean(String name) {
        return beans.get(name);
    }
}