package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.fixture.AuctionFixture;
import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import io.crowdcode.speedbay.auction.service.AuctionService;
import io.crowdcode.speedbay.common.AnsiColor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BusinessLogicConfiguration.class)
public class BusinessLogicConfigurationSpringTest {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private ApplicationContext context;

    @Test
    public void testIfAuctionServiceWasInjected() throws Exception {
        assertNotNull(auctionService);
    }

    @Test
    @Repeat(100)
    @DirtiesContext
    public void testApplicationContextWithIntegration() throws Exception {
        Auction auction = AuctionFixture.buildDefaultAuction();
        Long auctionId = auctionService.placeAuction(
                auction.getTitle(),
                auction.getDescription(),
                auction.getMinAmount());


        auctionService.bidOnAuction(auctionId, BigDecimal.valueOf(11));
        Auction found = auctionService.findAuction(auctionId);

        assertThat(found.getHighestBid().getAmount().doubleValue(), is(11.0));
        assertThat(auctionService.findRunningAuctions(),hasSize(1));
    }

    @Test
    public void testPrintBeanNames() throws Exception {
        String[] names = context.getBeanDefinitionNames();
        for (String beanName: names) {
            Class<?> type = context.getType(beanName);
            String[] alias = context.getAliases(beanName);
            System.out.println(AnsiColor.blue(beanName+":"+type.getCanonicalName()+"|"+ Arrays.toString(alias)));
        }
    }


}
