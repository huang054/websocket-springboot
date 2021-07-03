package com.hj.websocket.mvc.code;


import org.springframework.web.socket.WebSocketMessage;

/**
 * @Author: 01396614
 * @Date: 2020/8/19 9:46
 * @description: TODO
 */
public interface Code {

    public Object codec(WebSocketMessage<?> message,String obj);

    public String decoder(Object object);

    public Object parse(String message , Class<?> clazz);

}
