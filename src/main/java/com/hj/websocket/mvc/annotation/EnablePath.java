package com.hj.websocket.mvc.annotation;

import com.hj.websocket.mvc.handler.DefaultWebSocketHandler;
import com.hj.websocket.mvc.path.PathScannerRegistrar;
import com.hj.websocket.mvc.processor.WebSocketBeanPostProcessor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: 01396614
 * @Date: 2020/8/10 11:46
 * @description: TODO
 */
@Target( ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({PathScannerRegistrar.class,WebSocketBeanPostProcessor.class, DefaultWebSocketHandler.class})
public @interface EnablePath {
}
