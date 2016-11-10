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
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BusinessLogicAnnotationConfiguration.class)
public class BusinessLogicAnnotationConfigurationTest {

    @Autowired
    private InMemoryStore<Auction> inMemoryStore;
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private ListableBeanFactory applicationContext;

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