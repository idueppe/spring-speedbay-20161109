package io.crowdcode.speedbay.auction.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Configuration
@ComponentScan(
        basePackages = {
                "io.crowdcode.speedbay.auction",
                "io.crowdcode.speedbay.common.inmemory" // Variant 1
        },
//        basePackageClasses = {InMemoryStore.class}, // Variant 3
        excludeFilters = @ComponentScan.Filter
                (
                        type = FilterType.REGEX,
                        pattern = "io\\.crowdcode\\.speedbay\\.auction\\.config\\..*"
                )
)

public class BusinessLogicAnnotationConfiguration {

    // Variante 2
//    @Bean
//    public InMemoryStore<Auction> inMemoryStore() {
//        return new InMemoryStore<>();
//    }
}
