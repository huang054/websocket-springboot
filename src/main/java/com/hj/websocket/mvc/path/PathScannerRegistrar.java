package com.hj.websocket.mvc.path;

import com.hj.websocket.mvc.annotation.Path;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;

import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.List;

public class PathScannerRegistrar implements BeanFactoryAware,ImportBeanDefinitionRegistrar{


    private BeanFactory beanFactory;



    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        ClassPathScanner scanner = new ClassPathScanner(beanDefinitionRegistry);
        List<String> packages = AutoConfigurationPackages.get(this.beanFactory);
        scanner.setAnnotationClass(Path.class);
        scanner.registerFilters();
        scanner.doScan(StringUtils.toStringArray(packages));
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
