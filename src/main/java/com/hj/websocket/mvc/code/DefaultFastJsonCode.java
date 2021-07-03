package com.hj.websocket.mvc.code;


import com.alibaba.fastjson.JSONObject;

import org.springframework.web.socket.WebSocketMessage;

/**
 * @Author: 01396614
 * @Date: 2020/8/19 10:03
 * @description: TODO
 */
public class DefaultFastJsonCode implements Code {
    @Override
    public Object codec(WebSocketMessage<?> message,String obj) {
        JSONObject jsonObject = JSONObject.parseObject(message.getPayload().toString());
        return jsonObject.get(obj);
    }

    @Override
    public String decoder(Object object) {
        return JSONObject.toJSON(object).toString();
    }

    @Override
    public Object parse(String message, Class<?> clazz) {
        return JSONObject.parseObject(message,clazz);
    }


}
