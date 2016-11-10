package io.crowdcode.speedbay.auction.util;

import io.crowdcode.speedbay.auction.exception.AuctionExpiredException;
import io.crowdcode.speedbay.auction.exception.AuctionNotFoundException;
import io.crowdcode.speedbay.auction.exception.BidTooLowException;
import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.service.AuctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.crowdcode.speedbay.common.AnsiColor.green;
import static io.crowdcode.speedbay.common.AnsiColor.red;


/**
 * @author Ingo Düppe (Crowdcode)
 */

@Slf4j
public class LogBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info(red("GOT BEAN {} BEFORE"), beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info(red("GOT BEAN {} AFTER"), beanName);
        if (bean instanceof AuctionService && "auctionServiceBean".equals(beanName)) {
            return new ProxyAuctionService((AuctionService) bean);
        }
        return bean;
    }

    private class ProxyAuctionService implements AuctionService {

        private AuctionService target;
        private Map<Long, Auction> cache = new HashMap<>();

        public ProxyAuctionService(AuctionService target) {
            this.target = target;
        }

        public Long placeAuction(String title, String description, BigDecimal minAmount) {
            return target.placeAuction(title, description, minAmount);
        }

        public Auction findAuction(Long auctionId) {
            log.info(green("Cached"));
            if (!cache.containsKey(auctionId)) {
                 cache.put(auctionId, target.findAuction(auctionId));
            }
            return cache.get(auctionId);
        }

        public List<Auction> findRunningAuctions() {
            return target.findRunningAuctions();
        }

        public List<Auction> findExpiredAuctions() {
            return target.findExpiredAuctions();
        }

        public void bidOnAuction(Long auctionId, BigDecimal amount) throws AuctionNotFoundException, AuctionExpiredException, BidTooLowException {
            target.bidOnAuction(auctionId, amount);
        }


    }
}
