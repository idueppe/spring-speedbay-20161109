package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.DomainFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DomainFactoryConfiguration.class)
public class DomainFactoryConfigurationTest {

    @Autowired
    private DomainFactory domainFactory;

    @Test
    public void testDomainFactory() throws Exception {
        assertTrue(domainFactory.createAuction() != domainFactory.createAuction());
    }

    @Test
    public void testDomainFactoryBids() throws Exception {
        assertTrue(domainFactory.createBid() != domainFactory.createBid());
    }

    @Test
    public void testHasAtLeastOneBid() throws Exception {
        assertThat(domainFactory.createAuction().getBids(), hasSize(2));

    }

    @Test
    public void testBids() throws Exception {
        domainFactory.createAuction().getBids().forEach((b)-> System.out.println(b.getAmount()));

    }
}