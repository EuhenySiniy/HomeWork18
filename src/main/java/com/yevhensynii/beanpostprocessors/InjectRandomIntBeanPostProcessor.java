package com.yevhensynii.beanpostprocessors;

import com.yevhensynii.annotations.InjectRandomInt;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

@Component
public class InjectRandomIntBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            InjectRandomInt randomInt = declaredField.getAnnotation(InjectRandomInt.class);
            if (randomInt != null) {
                int min = randomInt.min();
                int max = randomInt.max();
                Random random = new Random();
                int result = min + random.nextInt(max - min);
                declaredField.setAccessible(true);
                if (declaredField.getDeclaringClass().getSimpleName().equals("Integer")) {
                    ReflectionUtils.setField(declaredField, bean, result);
                } else {
                    throw new IllegalArgumentException("Field is not integer");
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
