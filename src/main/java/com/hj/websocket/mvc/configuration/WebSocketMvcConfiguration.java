package com.hj.websocket.mvc.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @Author: 01396614
 * @Date: 2020/8/11 14:35
 * @description: TODO
 */

@ConfigurationProperties(prefix = "websocket.mvc")
public class WebSocketMvcConfiguration {

    private String classRequestMapping;

    private String methodRequestMapping;

    public String getClassRequestMapping() {
        return classRequestMapping;
    }

    public void setClassRequestMapping(String classRequestMapping) {
        this.classRequestMapping = classRequestMapping;
    }

    public String getMethodRequestMapping() {
        return methodRequestMapping;
    }

    public void setMethodRequestMapping(String methodRequestMapping) {
        this.methodRequestMapping = methodRequestMapping;
    }
}