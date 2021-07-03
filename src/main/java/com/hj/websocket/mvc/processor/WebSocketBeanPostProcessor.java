package com.hj.websocket.mvc.processor;



import com.hj.websocket.mvc.annotation.Path;
import com.hj.websocket.mvc.data.WebSocketBeans;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @Author: 01396614
 * @Date: 2020/8/10 10:55
 * @description: TODO
 */
public class WebSocketBeanPostProcessor implements BeanPostProcessor {

    private static final LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (AnnotatedElementUtils.hasAnnotation(bean.getClass(), Path.class)) {
            Path path = AnnotationUtils.findAnnotation(bean.getClass(), Path.class);
            BeanWrapper beanWrapper = new BeanWrapper();
            beanWrapper.setObject(bean);
            Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
            for (Method method : methods){
                if (method.isAnnotationPresent(Path.class)){
                    MethodWrapper methodWrapper = new MethodWrapper();
                    methodWrapper.setMethod(method);
                    methodWrapper.setParameterNames(parameterNameDiscoverer.getParameterNames(method));
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    methodWrapper.setParameterTypes(parameterTypes);
                    methodWrapper.setReturnType(method.getReturnType());
                    beanWrapper.getMethodWrapperMap().put(AnnotationUtils.findAnnotation(method,Path.class).value(),methodWrapper);
                }
            }
            WebSocketBeans.webSocketBeans.put(path.value(),beanWrapper);
        }
        return bean;
    }
}