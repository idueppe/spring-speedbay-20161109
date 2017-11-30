package io.crowdcode.speedbay.auction.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BusinessLogicAnnotationConfiguration.class)
@EnableAspectJAutoProxy
public class AdviceConfiguration {
}
