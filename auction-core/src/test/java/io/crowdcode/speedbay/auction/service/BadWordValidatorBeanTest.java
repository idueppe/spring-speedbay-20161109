package io.crowdcode.speedbay.auction.service;

import io.crowdcode.speedbay.auction.config.BadWordValidatorConfiguration;
import io.crowdcode.speedbay.auction.config.BusinessLogicAnnotationConfiguration;
import io.crowdcode.speedbay.auction.exception.BadWordException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes =
                {
                        BusinessLogicAnnotationConfiguration.class,
                        BadWordValidatorConfiguration.class
                })
public class BadWordValidatorBeanTest {

    @Autowired
    private BadWordValidator validator;

    @Test(expected = BadWordException.class)
    public void testBadWordCheck() throws Exception {
        validator.checkBadWords("PERL");
    }

//  Nicht so:
//
//    @Test
//    public void test() throws Exception {
//        try {
//            validator.checkBadWords("PERL");
//            fail();
//        } catch (BadWordException ex) {
//            assertTrue(true);
//        }
//    }

    @Test
    public void testIsValid() throws Exception {
        assertTrue(validator.isInvalid("PERL"));
    }

    @Test
    public void testRegex() throws Exception {
        String[] splitA = "PHP,PERL,C++,.NET,NodeJS,ReleaseZyklen".split("\\,\\s?");
        System.out.println(Arrays.toString(splitA));
        assertThat(splitA.length,is(6));
        String[] splitB = "PHP,PERL, C++, .NET,\tNodeJS,\nReleaseZyklen".split("\\,\\s?");
        System.out.println(Arrays.toString(splitB));
        assertThat(splitB.length,is(6));
    }
}