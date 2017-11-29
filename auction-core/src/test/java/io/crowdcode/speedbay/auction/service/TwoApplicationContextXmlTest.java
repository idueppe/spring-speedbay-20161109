package io.crowdcode.speedbay.auction.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:application*.xml"})
public class TwoApplicationContextXmlTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testApplicationContext() throws Exception {
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

    }
}