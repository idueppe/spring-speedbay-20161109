package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.util.LogBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BusinessLogicAnnotationConfiguration.class)
public class LogBeanPostProcessorConfiguration {

    @Bean
    public static LogBeanPostProcessor logBeanPostProcessor() {
        return new LogBeanPostProcessor();
    }
}
