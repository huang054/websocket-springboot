package com.hj.websocket.mvc.configuration;


import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: 01396614
 * @Date: 2020/8/19 14:57
 * @description: TODO
 */
public class BeanDefinitionRegistrarConfiguration implements  EnvironmentAware, ImportBeanDefinitionRegistrar {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        if (environment.getProperty("web.socket.mvc.codingPath")!=null){
            try {
                RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Class.forName(environment.getProperty("web.socket.mvc.codingPath")));
                beanDefinitionRegistry.registerBeanDefinition(Class.forName(environment.getProperty("web.socket.mvc.codingPath")).getName(),rootBeanDefinition);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
