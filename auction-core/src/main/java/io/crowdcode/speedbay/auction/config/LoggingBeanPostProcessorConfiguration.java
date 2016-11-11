package io.crowdcode.speedbay.auction.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static io.crowdcode.speedbay.common.AnsiColor.yellow;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Slf4j
@Configuration
public class LoggingBeanPostProcessorConfiguration {


    @Bean
    public static BeanPostProcessor beanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                log.info(yellow("Before Initialization {}"), beanName);
                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                log.info(yellow("After Initialization {}"), beanName);
                return bean;
            }
        };
    }

}
