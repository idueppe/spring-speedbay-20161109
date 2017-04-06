package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.util.CachingBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by idueppe on 06.04.17.
 */
@Configuration
@Import(BusinessLogicConfiguration.class)
public class CachingBeanPostProcessorConfiguration {

    @Bean
    public static CachingBeanPostProcessor createCaching() {
        return new CachingBeanPostProcessor();
    }
}
