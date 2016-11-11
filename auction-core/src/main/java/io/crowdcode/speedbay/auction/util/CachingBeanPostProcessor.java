package io.crowdcode.speedbay.auction.util;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static io.crowdcode.speedbay.common.AnsiColor.blue;
import static io.crowdcode.speedbay.common.AnsiColor.red;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Slf4j
public class CachingBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info(blue("Bean {} before initialization..."), beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info(blue("Bean {} after initialization..."), beanName);
        if (bean instanceof AuctionRepository) {
            log.info(red("exchange with proxy"));
            return new AuctionRepositoryCache((AuctionRepository) bean);
        }
        return bean;
    }

    private class AuctionRepositoryCache implements AuctionRepository {

        private final AuctionRepository target;

        private List<Auction> cachedAuctions;

        private LocalDateTime lastUpdate = LocalDateTime.MIN;

        public AuctionRepositoryCache(AuctionRepository target) {
            this.target = target;
        }

        public Optional<Auction> find(Long auctionId) {
            return target.find(auctionId);
        }

        public Auction findOne(Long auctionId) {
            return target.findOne(auctionId);
        }

        public List<Auction> findAll() {
            if (cachedAuctions == null || lastUpdate.plusSeconds(30).isBefore(LocalDateTime.now())) {
                cachedAuctions = target.findAll();
                lastUpdate = LocalDateTime.now();
            }
            return cachedAuctions;
        }

        public Auction save(Auction auction) {
            return target.save(auction);
        }


    }
}
