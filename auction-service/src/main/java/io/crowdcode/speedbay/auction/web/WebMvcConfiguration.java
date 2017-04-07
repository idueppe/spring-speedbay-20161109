package io.crowdcode.speedbay.auction.web;

import io.crowdcode.speedbay.auction.config.BusinessLogicAnnotationConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Slf4j
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "io.crowdcode.speedbay.auction.web")
@Import(BusinessLogicAnnotationConfiguration.class)
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
        configurer.ignoreAcceptHeader(false);
        configurer.defaultContentType(MediaType.TEXT_PLAIN);

        configurer.mediaType("html", MediaType.TEXT_HTML);
        configurer.mediaType("xml", MediaType.APPLICATION_XML);
        configurer.mediaType("json", MediaType.APPLICATION_JSON);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.debug("adding resource handlers");
        registry.addResourceHandler("/resources/**") //
                .addResourceLocations("/resources/");
        registry.addResourceHandler("/images/**") //
                .addResourceLocations("/images/");
    }
}
