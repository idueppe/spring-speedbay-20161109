package io.crowdcode.speedbay.auction.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/")
public class InfoController {

    @GetMapping("/{name}")
    public String info(@PathVariable("name") @NotNull String name) {
        return "Every thing is fine, "+name+"!";
    }
}
