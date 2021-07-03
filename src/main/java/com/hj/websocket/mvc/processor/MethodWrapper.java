package com.hj.websocket.mvc.processor;


import java.lang.reflect.Method;

/**
 * @Author: 01396614
 * @Date: 2020/8/10 11:07
 * @description: TODO
 */
public class MethodWrapper {

    private Method method;

    private Class<?>[] parameterTypes;

    private Class<?> returnType;

    private String[] parameterNames;


    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public String[] getParameterNames() {
        return parameterNames;
    }

    public void setParameterNames(String[] parameterNames) {
        this.parameterNames = parameterNames;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }
}
