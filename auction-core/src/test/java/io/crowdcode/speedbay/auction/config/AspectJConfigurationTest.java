package io.crowdcode.speedbay.auction.config;


import io.crowdcode.speedbay.auction.fixture.AuctionFixture;
import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import io.crowdcode.speedbay.auction.service.AuctionService;
import io.crowdcode.speedbay.common.AnsiColor;
import io.crowdcode.speedbay.common.inmemory.InMemoryStore;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AspectJConfiguration.class)
public class AspectJConfigurationTest {


    @Autowired
    private AuctionService auctionService;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private InMemoryStore<Auction> inMemoryStore;

    @Autowired
    private ApplicationContext context;

    @Test
    public void testConfigTest() throws Exception {
        assertNotNull(inMemoryStore);
        assertNotNull(auctionService);
        assertNotNull(auctionRepository);
    }

    @Test
    public void testIfBeansAreCorrectlyWiredTogether() throws Exception {
        Auction fixture = AuctionFixture.buildDefaultAuction();
        Long auctionId = auctionService
                .placeAuction(
                        fixture.getTitle(),
                        fixture.getDescription(),
                        fixture.getMinAmount());

        Auction auction = auctionService.findAuction(auctionId);
        assertNotNull(auction);

        auctionService.bidOnAuction(auctionId, BigDecimal.valueOf(11));

        Auction found = auctionService.findAuction(auctionId);

        assertThat(found.getHighestBid().getAmount().doubleValue(), is(11.0));
    }

    @Test
    public void testLogBeanNames() throws Exception {
        for (String beanName : context.getBeanDefinitionNames()) {
            log.info(AnsiColor.blue(beanName));
        }
    }

}