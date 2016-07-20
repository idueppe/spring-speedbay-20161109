package io.crowdcode.speedbay.common.time;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class TimeMachineTest {

    @Test
    public void testMinusFixed() throws Exception {
        TimeMachine.setFixed();
        LocalDateTime now = TimeMachine.now();
        TimeMachine.clockMinus(2, ChronoUnit.MINUTES);
        LocalDateTime before = TimeMachine.now();
        assertThat(before.isBefore(now), is(true));

    }
}