package io.crowdcode.speedbay.auction.model;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DomainFactoryBean implements DomainFactory, ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public Auction createAuction() {
        return context.getBean("auction", Auction.class);
    }

    @Override
    public Bid createBid() {
        return context.getBean("bid", Bid.class);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
