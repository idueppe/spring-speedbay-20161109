package io.crowdcode.speedbay.auctionservice.controller;

import io.crowdcode.speedbay.auction.model.Message;
import io.crowdcode.speedbay.auction.service.ApplicationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by idueppe on 22.06.17.
 */
@RestController
public class AuctionerLogController {

    @Autowired
    private ApplicationLogService applicationLogService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @PostMapping("/")
    public void logMessage(@RequestBody String message) {
        applicationLogService.log(message);
    }

    @GetMapping("/")
    public List<Message> messages() {
        return applicationLogService.lastLogs(Duration.of(5, ChronoUnit.SECONDS));
    }
}
