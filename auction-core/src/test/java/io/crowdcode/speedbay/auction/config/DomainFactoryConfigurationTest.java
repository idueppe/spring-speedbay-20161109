package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.model.DomainFactory;
import io.crowdcode.speedbay.common.AnsiColor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DomainFactoryConfiguration.class)
public class DomainFactoryConfigurationTest {

    @Autowired
    private DomainFactory domainFactory;


    @Test
    public void testPrototype() throws Exception {
        Auction one = domainFactory.createAuction();
        Auction two = domainFactory.createAuction();

        log.debug(AnsiColor.red("One {}"), one.hashCode());
        log.debug(AnsiColor.red("Two {}"), two.hashCode());

        assert(one != two);
//        assertThat(one, is(not(two)));
    }
}