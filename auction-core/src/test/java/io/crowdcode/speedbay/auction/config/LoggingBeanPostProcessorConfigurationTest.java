package io.crowdcode.speedbay.auction.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes =  {
                LoggingBeanPostProcessorConfiguration.class,
                BusinessLogicAnnotationConfiguration.class
        })
public class LoggingBeanPostProcessorConfigurationTest {

    @Test
    public void testEmpty() throws Exception {


    }
}