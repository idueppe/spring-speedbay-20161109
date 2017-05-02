package io.crowdcode.speedbay.auction.repository.jpa;

import io.crowdcode.speedbay.auction.config.BusinessLogicAnnotationConfiguration;
import io.crowdcode.speedbay.auction.config.JpaPersistenceContextConfiguration;
import io.crowdcode.speedbay.auction.fixture.AuctionFixture;
import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BusinessLogicAnnotationConfiguration.class, JpaPersistenceContextConfiguration.class})
@ActiveProfiles("jpa")
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuctionRepositoryJpaBeanTest {

    @Autowired
    private AuctionRepository auctionRepository;

    @Test
    @Commit
    public void test_1_AuctionCreate() throws Exception {
        Auction auction = AuctionFixture.buildDefaultAuction();
        auctionRepository.save(auction);
        assertNotNull(auction.getId());
    }

    @Test
    @Commit
    public void test_2_FindAllAuctions() throws Exception {
        auctionRepository.findAll().forEach(System.out::println);
    }


}