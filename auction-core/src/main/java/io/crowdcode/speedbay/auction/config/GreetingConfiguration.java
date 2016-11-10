package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Greeting;
import io.crowdcode.speedbay.auction.model.GreetingGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static io.crowdcode.speedbay.common.AnsiColor.green;
import static io.crowdcode.speedbay.common.AnsiColor.red;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Slf4j
@Configuration
public class GreetingConfiguration {

    @Bean
    public GreetingGroup greetingGroup() {
        GreetingGroup group = new GreetingGroup();
        group.getGreetings().add(greeting());
        group.getGreetings().add(greeting());
        return group;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Greeting greeting() {
        log.info(red("Creating Greeting Bean"));
        Greeting greeting = new Greeting();
        greeting.setMessage(green(""+System.currentTimeMillis()));
        return greeting;
    }
}
