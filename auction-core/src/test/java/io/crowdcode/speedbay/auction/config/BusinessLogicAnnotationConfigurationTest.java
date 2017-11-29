package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.model.AuctionFixture;
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

import static org.junit.Assert.assertNotNull;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BusinessLogicAnnotationConfiguration.class)
public class BusinessLogicAnnotationConfigurationTest {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private InMemoryStore<Auction> inMemoryStore;

    @Autowired
    private ApplicationContext applicationContext;


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
    }

    @Test
    public void testLogBeanNames() throws Exception {
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            log.info(AnsiColor.blue(beanName));
        }
    }


}