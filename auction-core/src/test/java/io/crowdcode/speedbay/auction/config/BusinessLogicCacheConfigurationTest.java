package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.fixture.AuctionFixture;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import io.crowdcode.speedbay.auction.service.AuctionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BusinessLogicCacheConfiguration.class)
public class BusinessLogicCacheConfigurationTest {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private AuctionRepository auctionRepository;

    @Test
    public void testCaching() throws Exception {

        auctionRepository.save(AuctionFixture.buildDefaultAuction());

        auctionService.findRunningAuctions();

        auctionRepository.save(AuctionFixture.buildDefaultAuction());

        assertThat(auctionService.findRunningAuctions(), hasSize(1));

//  assertThat(auctionRepository.findAll(), hasSize(2));

    }
}