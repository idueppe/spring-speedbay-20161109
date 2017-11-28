package io.crowdcode.speedbay.auction.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AuctionServiceBeanTest {

    @Test
    public void testApplicationContext() throws Exception {
        // Instanzieren einen Spring (Application) Context
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // Bean by Name
//        String name = (String) context.getBean("hugo");
//        String nameService = (String) context.getBean("auctionService");

        // AuctionService Bean by Name And Type aus dem Kontext holen.
        AuctionService serviceByNameAndType = context.getBean("auctionService", AuctionService.class);

        assertNotNull(serviceByNameAndType);
        AuctionService serviceByName = (AuctionService) context.getBean("hugo"); // Gefahr falscher Typ
        assertTrue(serviceByNameAndType == serviceByName);

        AuctionService serviceByType = context.getBean(AuctionServiceBean.class); // Mehr als einer
        assertTrue(serviceByNameAndType == serviceByType);

        Map<String, AuctionService> serviceList = context.getBeansOfType(AuctionService.class);

        Arrays.stream(context.getBeanDefinitionNames())
                .map(n -> "> " + n + Arrays.toString(context.getAliases(n)))
                .forEach(System.out::println);

    }
}