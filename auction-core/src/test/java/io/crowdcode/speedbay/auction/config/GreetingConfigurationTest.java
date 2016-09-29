package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Greeting;
import io.crowdcode.speedbay.auction.model.GreetingGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.crowdcode.speedbay.common.AnsiColor.red;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=GreetingConfiguration.class)
public class GreetingConfigurationTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private GreetingGroup greetingGroup;

    @Test
    public void testGreetingGroup() throws Exception {
        greetingGroup.getGreetings().forEach( (g) -> System.out.println(red(g.getMessage()+""+g.hashCode())) );

    }

    @Test
    public void testGreetings() throws Exception {
        Greeting one = context.getBean(Greeting.class);
        Thread.sleep(100);
        Greeting two = context.getBean(Greeting.class);

        System.out.println(one.getMessage());
        System.out.println(two.getMessage());

    }
}