package io.crowdcode.speedbay.auction.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

import static io.crowdcode.speedbay.common.AnsiColor.red;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Slf4j
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


    @Value("classpath:application.properties")
    private Resource resource;
    @Value("#{systemProperties['java.io.tmpdir']}")
    private String tmpDir;

    @Bean
    public String wert() throws IOException {
        log.info(red("tmpdir: {}"), tmpDir);
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        return (String) properties.get("key");

    }

}
