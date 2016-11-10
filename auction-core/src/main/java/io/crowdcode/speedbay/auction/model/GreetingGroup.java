package io.crowdcode.speedbay.auction.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Getter @Setter
public class GreetingGroup {

    private List<Greeting> greetings = new ArrayList<>();
}
