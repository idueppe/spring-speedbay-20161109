package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.service.BadWordValidator;
import io.crowdcode.speedbay.auction.service.BadWordValidatorBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Configuration
public class BadWordValidatorConfiguration {

    @Bean
    public BadWordValidator badWordValidator() {
        return new BadWordValidatorBean();
    }
}
