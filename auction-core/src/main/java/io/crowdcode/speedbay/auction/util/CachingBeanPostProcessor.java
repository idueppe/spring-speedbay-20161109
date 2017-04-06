package io.crowdcode.speedbay.auction.util;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import io.crowdcode.speedbay.common.AnsiColor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class CachingBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.debug(AnsiColor.yellow("Post processing bean {} before initialization."), beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.debug(AnsiColor.yellow("Post processing bean {} after initialization."), beanName);
        if (bean instanceof AuctionRepository) {
            return new CachingAuctionRepository((AuctionRepository) bean);
        }

        return bean;
    }

    public static class CachingAuctionRepository implements AuctionRepository {
        private AuctionRepository target;
        private Map<Long, Auction> cache = new HashMap<>();

        public CachingAuctionRepository(AuctionRepository target) {
            this.target = target;
        }

        @Override
        public Optional<Auction> find(Long auctionId) {
            if (!cache.containsKey(auctionId)) {
                cache.put(auctionId, target.findOne(auctionId));
            }

            return Optional.ofNullable(cache.get(auctionId));
        }

        @Override
        public Auction findOne(Long auctionId) {
            if (!cache.containsKey(auctionId)) {
                cache.put(auctionId, target.findOne(auctionId));
            }

            return cache.get(auctionId);
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
}
