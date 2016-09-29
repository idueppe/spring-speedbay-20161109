package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.util.CachingBeanPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Configuration
@Import(BusinessLogicConfiguration.class)
public class BusinessLogicCacheConfiguration {

    @Bean
    public static BeanPostProcessor cachingBeans() {
        return new CachingBeanPostProcessor();
    }
}
