package com.hj.websocket.mvc.processor;


import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 01396614
 * @Date: 2020/8/10 11:05
 * @description: TODO
 */
public class BeanWrapper {

    private Object object;

    private Map<String,MethodWrapper> methodWrapperMap = new HashMap<>();

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Map<String, MethodWrapper> getMethodWrapperMap() {
        return methodWrapperMap;
    }

    public void setMethodWrapperMap(Map<String, MethodWrapper> methodWrapperMap) {
        this.methodWrapperMap = methodWrapperMap;
    }
}