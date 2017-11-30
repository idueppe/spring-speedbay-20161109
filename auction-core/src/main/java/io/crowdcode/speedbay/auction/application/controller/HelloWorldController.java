package io.crowdcode.speedbay.auction.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HelloWorldController {

    @RequestMapping(path="/hello")
    public String sayHello() {
        return "Hello!";
    }

}
