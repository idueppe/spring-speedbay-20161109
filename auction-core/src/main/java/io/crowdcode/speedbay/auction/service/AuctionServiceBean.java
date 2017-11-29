package io.crowdcode.speedbay.auction.service;

import io.crowdcode.speedbay.auction.exception.AuctionExpiredException;
import io.crowdcode.speedbay.auction.exception.AuctionNotFoundException;
import io.crowdcode.speedbay.auction.exception.BidTooLowException;
import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.model.Bid;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import io.crowdcode.speedbay.common.AnsiColor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuctionServiceBean implements AuctionService, InitializingBean {

    // Autowired mit Field Injection
    //  @Autowired
    // @Qualifier("auctionRepository")
    private AuctionRepository auctionRepository;

    // Autowired mit Constructor Injection
    @Autowired
    // @Qualifier("auctionRepository")
    public AuctionServiceBean(AuctionRepository repository) {
        this.auctionRepository = repository;
    }

    // Autowired mit Setter Injection
    public AuctionServiceBean() {
    }

    @PostConstruct
    public void postConstructorKannHeissenWieSieWill() {
        log.info(AnsiColor.green("------------------------------------------ POSTCONSTRUCT"));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(AnsiColor.green("------------------------------------------ afterPropertiesSet"));

    }

    public void init() {
        log.info(AnsiColor.green("------------------------------------------ Init"));
    }


    // @Autowired
    // @Qualifier("auctionService")
    public void setAuctionRepository(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @Override
    public Long placeAuction(String title, String description, BigDecimal minAmount) {
        Auction auction = new Auction()
                .setTitle(title)
                .setDescription(description)
                .setMinAmount(minAmount)
                .setBeginDate(LocalDateTime.now())
                .setExpireDate(LocalDateTime.now().plusMinutes(5));
        auctionRepository.save(auction);
        return auction.getId();
    }

    @Override
    public Auction findAuction(Long auctionId) {
        return auctionRepository.findOne(auctionId);
    }

    @Override
    public List<Auction> findRunningAuctions() {
        return auctionRepository
                .findAll()
                .parallelStream()
                .filter(Auction::isRunning)
                .collect(Collectors.toList());
    }

    @Override
    public List<Auction> findExpiredAuctions() {
        return auctionRepository
                .findAll()
                .parallelStream()
                .filter(Auction::isExpired)
                .collect(Collectors.toList());
    }

    @Override
    public void bidOnAuction(Long auctionId, BigDecimal amount) throws AuctionNotFoundException, AuctionExpiredException, BidTooLowException {
        Auction auction = auctionRepository.findOne(auctionId);
        if (auction == null) {
            throw new AuctionNotFoundException();
        }
        if (auction.getHighestBid().getAmount().compareTo(amount) > 0) {
            throw new BidTooLowException();
        }
        auction.getBids().add(new Bid().setAmount(amount).setEmail("security@context.not.avaible"));

        auctionRepository.save(auction);
    }

}
