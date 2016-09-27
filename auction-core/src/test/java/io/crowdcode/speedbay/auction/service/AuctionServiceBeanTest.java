package io.crowdcode.speedbay.auction.service;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class AuctionServiceBeanTest {

    @Test
    public void testApplicationContext() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        context.getBean(AuctionService.class);

        AuctionService auctionService = context.getBean("auctionService", AuctionService.class);

        assertNotNull(auctionService);

        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(
                        beanName -> System.out.println(beanName));

        context.close();

    }
}