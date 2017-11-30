package io.crowdcode.speedbay.auction.util;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import io.crowdcode.speedbay.common.AnsiColor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.List;
import java.util.Optional;

@Slf4j
public class CachablePostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info(AnsiColor.red("Before Initialization of {}"), beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info(AnsiColor.red("After Initialization of {}"), beanName);
        if (bean instanceof AuctionRepository) {
            log.info(AnsiColor.red("Found AuctionRepository {}"), beanName);
            return new CachableAuctionRepository(
                            new TransactionAuctionRepository(
                                    (AuctionRepository) bean));
        }
        return bean;
    }

    public static class TransactionAuctionRepository implements AuctionRepository {
        private AuctionRepository target;

        public TransactionAuctionRepository(AuctionRepository target) {
            this.target = target;
        }

        @Override
        public Optional<Auction> find(Long auctionId) {
            return target.find(auctionId);
        }

        @Override
        public Auction findOne(Long auctionId) {
            return target.findOne(auctionId);
        }

        @Override
        public List<Auction> findAll() {
            return target.findAll();
        }

        @Override
        public Auction save(Auction auction) {
            return target.save(auction);
        }

    }


    public static class CachableAuctionRepository implements AuctionRepository {

        private AuctionRepository target;

        private List<Auction> cachedList;

        public CachableAuctionRepository(AuctionRepository target) {
            this.target = target;
        }

        @Override
        public Optional<Auction> find(Long auctionId) {
            return target.find(auctionId);
        }

        @Override
        public Auction findOne(Long auctionId) {
            return target.findOne(auctionId);
        }

        @Override
        public List<Auction> findAll() {
            // FIXME OMG
            if (cachedList == null) {
                cachedList = target.findAll();
            }
            return cachedList;
        }

        @Override
        public Auction save(Auction auction) {
            return target.save(auction);
        }


    }
}
