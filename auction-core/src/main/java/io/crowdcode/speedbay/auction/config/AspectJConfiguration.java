package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.util.CountBidAdvice;
import io.crowdcode.speedbay.auction.util.LoggingAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * Created by idueppe on 22.06.17.
 */
@Configuration
@Import(BusinessLogicAnnotationConfiguration.class)
@EnableAspectJAutoProxy
public class AspectJConfiguration {

    @Bean
    public LoggingAdvice loggingAdvice() {
        return new LoggingAdvice();
    }

    @Bean
    public CountBidAdvice countBidAdvice() {
        return new CountBidAdvice();
    }
}
