package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.common.inmemory.InMemoryStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = {
                "io.crowdcode.speedbay.auction"
//                , "io.crowdcode.speedbay.common"
        },
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = "io\\.crowdcode\\.speedbay\\.auction\\.config.*")
        }
)
public class BusinessLogicAnnotationConfiguration {

    @Bean
    public InMemoryStore<Auction> inMemoryStoreAuction() {
        return new InMemoryStore<>();
    }
}
