package io.crowdcode.speedbay.auction.service;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

public class AuctionServiceBeanTest {

    @Test
    public void testLoadApplicationContext() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        AuctionService auctionService =
                context.getBean("auctionService", AuctionService.class);
        assertNotNull(auctionService);

        context.close();

    }
}