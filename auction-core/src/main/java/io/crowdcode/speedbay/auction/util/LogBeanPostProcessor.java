package io.crowdcode.speedbay.auction.util;

import io.crowdcode.speedbay.common.AnsiColor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
public class LogBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.debug(AnsiColor.yellow("Post processing bean {} of type {} before initialization."), beanName, bean
                .getClass().getCanonicalName());

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.debug(AnsiColor.yellow("Post processing bean {} of type {} after initialization."), beanName, bean
                .getClass().getCanonicalName());
        return bean;
    }
}
