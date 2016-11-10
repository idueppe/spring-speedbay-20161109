package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.util.LogBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Configuration
@Import(BusinessLogicAnnotationConfiguration.class)
public class BeanPostProcessConfiguration {

    @Bean
    public static LogBeanPostProcessor logBeanPostProcessor() {
        return new LogBeanPostProcessor();
    }
}
