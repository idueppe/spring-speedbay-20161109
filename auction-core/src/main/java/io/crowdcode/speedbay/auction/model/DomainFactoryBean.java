package io.crowdcode.speedbay.auction.model;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class DomainFactoryBean implements DomainFactory, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public Auction createAuction() {
        return applicationContext.getBean(Auction.class);
    }

    @Override
    public Bid createBid() {
        return applicationContext.getBean(Bid.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
