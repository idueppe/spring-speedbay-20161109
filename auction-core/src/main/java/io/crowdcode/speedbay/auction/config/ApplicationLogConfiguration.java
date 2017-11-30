package io.crowdcode.speedbay.auction.config;


import io.crowdcode.speedbay.auction.repository.ApplicationLogRepository;
import io.crowdcode.speedbay.auction.repository.ApplicationLogRepositoryBean;
import io.crowdcode.speedbay.auction.service.ApplicationLogService;
import io.crowdcode.speedbay.auction.service.ApplicationLogServiceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DatabasePopulateConfiguration.class})
public class ApplicationLogConfiguration {

    @Bean
    public ApplicationLogRepository applicationLogRepositoryBean() {
        return new ApplicationLogRepositoryBean();
    }

    @Bean
    public ApplicationLogService applicationLogServiceBean() {
        return new ApplicationLogServiceBean();
    }

}
