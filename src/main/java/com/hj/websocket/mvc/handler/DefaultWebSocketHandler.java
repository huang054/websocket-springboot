package com.hj.websocket.mvc.handler;


import com.alibaba.fastjson.JSONObject;


import com.hj.websocket.mvc.code.Code;
import com.hj.websocket.mvc.code.DefaultFastJsonCode;
import com.hj.websocket.mvc.configuration.PropertyEditors;
import com.hj.websocket.mvc.configuration.WebSocketMvcConfiguration;
import com.hj.websocket.mvc.data.WebSocketBeans;
import com.hj.websocket.mvc.processor.BeanWrapper;
import com.hj.websocket.mvc.processor.MethodWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.socket.*;

import java.beans.PropertyEditor;



/**
 * @Author: 01396614
 * @Date: 2020/8/11 13:51
 * @description: TODO
 */
@EnableConfigurationProperties({WebSocketMvcConfiguration.class})
public class DefaultWebSocketHandler implements WebSocketHandler , ApplicationContextAware, InitializingBean {

    private Logger logger = LoggerFactory.getLogger(DefaultWebSocketHandler.class);

    private ApplicationContext applicationContext;

    private Code code;

    private final WebSocketMvcConfiguration webSocketMvcConfiguration;

    public DefaultWebSocketHandler(WebSocketMvcConfiguration webSocketMvcConfiguration) {
        this.webSocketMvcConfiguration = webSocketMvcConfiguration;
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        logger.info("connection success");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet()  {
        if (applicationContext.getEnvironment().getProperty("web.socket.mvc.codingPath")==null){
            code = new DefaultFastJsonCode();
        }else{
            code = applicationContext.getBean(Code.class);
        }
    }

    @Override
    public final void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        logger.info("receive message：{}", message.getPayload());
        BeanWrapper beanWrapper = WebSocketBeans.webSocketBeans.get(code.codec(message,webSocketMvcConfiguration.getClassRequestMapping()).toString());
        MethodWrapper methodWrapper = beanWrapper.getMethodWrapperMap().get(code.codec(message,webSocketMvcConfiguration.getMethodRequestMapping()).toString());
        Object result;
        if (methodWrapper.getParameterTypes() == null){
            result = ReflectionUtils.invokeJdbcMethod(methodWrapper.getMethod(),beanWrapper.getObject());
        }else {
            Object[] objects = new Object[methodWrapper.getParameterTypes().length];
            for (int i = 0; i < methodWrapper.getParameterTypes().length; i++) {
                PropertyEditor propertyEditor = PropertyEditors.defaultEditors.get(methodWrapper.getParameterTypes()[i]);
                if (propertyEditor !=null){
                    propertyEditor.setAsText(code.codec(message,methodWrapper.getParameterNames()[i]).toString());
                    objects[i] = propertyEditor.getValue();
                }else if(methodWrapper.getParameterTypes()[i].getName().equals(WebSocketSession.class.getName())){
                    objects[i] =session;
                }else if(methodWrapper.getParameterTypes()[i].equals(String.class)){
                    objects[i] =code.codec(message,methodWrapper.getParameterNames()[i]).toString();
                } else{
                    objects[i] =code.parse(code.codec(message,methodWrapper.getParameterNames()[i]).toString(), methodWrapper.getParameterTypes()[i]);
                }
            }


            result = ReflectionUtils.invokeMethod(methodWrapper.getMethod(), beanWrapper.getObject(), objects);
        }
       if (methodWrapper.getReturnType().equals(Void.TYPE)) {
        return;
       }
    WebSocketMessage<?> resultMessage = new TextMessage(code.decoder(result));

     session.sendMessage(resultMessage);


}

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}