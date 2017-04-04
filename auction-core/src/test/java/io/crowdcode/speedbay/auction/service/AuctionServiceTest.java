package io.crowdcode.speedbay.auction.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by idueppe on 04.04.17.
 */
public class AuctionServiceTest {

    @Test
    public void testAuctionFromContext() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        assertThat(context.getBean("auctionService"), is(notNullValue()));
    }
}