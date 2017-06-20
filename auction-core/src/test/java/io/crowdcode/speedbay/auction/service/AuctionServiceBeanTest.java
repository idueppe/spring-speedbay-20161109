package io.crowdcode.speedbay.auction.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * Created by idueppe on 20.06.17.
 */
public class AuctionServiceBeanTest {

    @Test
    public void testApplicationContext() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AuctionService auctionService = context.getBean("auctionService", AuctionService.class);
        assertNotNull(auctionService);
    }
}