package io.crowdcode.speedbay.auction.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class AuctionServiceBean implements AuctionService, ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        ((GenericApplicationContext)applicationContext).close(); DO NOT DO THIS
    }
}
