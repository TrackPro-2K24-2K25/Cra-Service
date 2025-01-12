package org.youcode.trackprocraservice.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.youcode.trackprocraservice.utils.EncryptionUtil;

import java.lang.reflect.Field;

@Component
public class EncryptionBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // Encrypt fields before the bean is initialized
        encryptFields(bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // Decrypt fields after the bean is initialized
        decryptFields(bean);
        return bean;
    }

    private void encryptFields(Object bean) {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Encrypted.class)) {
                field.setAccessible(true);
                try {
                    String value = (String) field.get(bean);
                    if (value != null) {
                        String encryptedValue = EncryptionUtil.encrypt(value);
                        field.set(bean, encryptedValue);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Encryption failed for field: " + field.getName(), e);
                }
            }
        }
    }

    private void decryptFields(Object bean) {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Encrypted.class)) {
                field.setAccessible(true);
                try {
                    String value = (String) field.get(bean);
                    if (value != null) {
                        String decryptedValue = EncryptionUtil.decrypt(value);
                        field.set(bean, decryptedValue);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Decryption failed for field: " + field.getName(), e);
                }
            }
        }
    }
}