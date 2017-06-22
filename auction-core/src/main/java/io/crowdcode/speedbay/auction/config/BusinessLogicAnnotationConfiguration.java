package io.crowdcode.speedbay.auction.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = {
                "io.crowdcode.speedbay.auction",
                "io.crowdcode.speedbay.common"
        },
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = "io\\.crowdcode\\.speedbay\\.auction\\.config.*"),
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = ".*ApplicationLog.*"
                )
        }
)
public class BusinessLogicAnnotationConfiguration {
}
