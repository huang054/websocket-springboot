package com.hj.websocket.mvc.annotation;




import java.lang.annotation.*;

/**
 * @Author: 01396614
 * @Date: 2020/8/10 11:01
 * @description: TODO
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Path {

    String value();
}
