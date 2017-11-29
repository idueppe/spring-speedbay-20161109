package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.model.AuctionFixture;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import io.crowdcode.speedbay.auction.service.AuctionService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class BusinessLogicConfigurationTest {

    private ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new AnnotationConfigApplicationContext(BusinessLogicConfiguration.class);
    }

    @Test
    public void testBeanNames() throws Exception {
        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println);
    }

    @Test
    public void testApplicationContext() throws Exception {
        //TODO AuctionRepository Bean aus dem ApplicationContext laden
        AuctionRepository repository = context.getBean(AuctionRepository.class);

        assertNotNull(repository);

        // TODO AuctionService Bean aus dem ApplicationContext laden
        AuctionService service = context.getBean("auctionService", AuctionService.class);

        assertNotNull(service);

        Auction auction = AuctionFixture.buildAuction();

        Long auctionId = service.placeAuction(
                auction.getTitle(),
                auction.getDescription(),
                auction.getMinAmount());

        assertThat(auctionId, is(notNullValue()));

        service.bidOnAuction(auctionId, BigDecimal.valueOf(11));

        Auction found = service.findAuction(auctionId);

        assertThat(found.getHighestBid().getAmount().doubleValue(), is(11.0));
    }
}