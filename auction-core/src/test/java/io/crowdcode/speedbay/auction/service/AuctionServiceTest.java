package io.crowdcode.speedbay.auction.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class AuctionServiceTest {

    @Test
    public void testApplicationContext() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AuctionService auctionService = context.getBean("auctionService", AuctionService.class);
        assertNotNull(auctionService);

        ((ClassPathXmlApplicationContext)context).close();
    }
}