package io.crowdcode.speedbay.greeting.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by idueppe on 22.05.17.
 */
@RestController
public class GreetingController {

    @RequestMapping("/")
    public String sayHello() {
        return "Hello ";
    }
}
