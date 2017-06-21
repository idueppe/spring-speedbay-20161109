package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.fixture.AuctionFixture;
import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import io.crowdcode.speedbay.auction.service.AuctionService;
import io.crowdcode.speedbay.common.AnsiColor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertNotNull;

/**
 * Created by idueppe on 05.04.17.
 */
public class BusinessLogicConfigurationTest {

    private ApplicationContext context;

    @Before
    public void setup() {
        context = new AnnotationConfigApplicationContext(BusinessLogicConfiguration.class);
    }

    @Test
    public void testApplicationContextWithIntegration() throws Exception {
        //AuctionRepository Bean aus dem ApplicationContext laden

        AuctionRepository repository = context.getBean("auctionRepository", AuctionRepository.class);
//        AuctionRepository repository = (AuctionRepository) context.getBean("auctionRepository");

        assertNotNull(repository);

        //AuctionService Bean aus dem ApplicationContext laden

        AuctionService service = context.getBean(AuctionService.class);

        assertNotNull(service);


        //Platziere eine Auction. Verwende AuctionFixture.buildAuction() um eine Auction zu erstellen.

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

    @Test
    public void testPrintBeanNames() throws Exception {
        String[] names = context.getBeanDefinitionNames();
        for (String beanName: names) {
            Class<?> type = context.getType(beanName);
            String[] alias = context.getAliases(beanName);
            System.out.println(AnsiColor.green(beanName+":"+type.getCanonicalName()+"|"+ Arrays.toString(alias)));
        }
    }

    @Test
    public void testIdAfterSave() throws Exception {
        AuctionRepository repository = context.getBean(AuctionRepository.class);

        Auction auction = AuctionFixture.buildDefaultAuction();

        assertThat(auction.getId(), is(nullValue()));

        repository.save(auction);

        Assert.assertThat(auction.getId(), is(notNullValue()));

        Assert.assertThat(auction.getId(), is(greaterThan(1l)));
    }

}