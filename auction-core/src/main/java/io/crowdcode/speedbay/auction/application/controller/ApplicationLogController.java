package io.crowdcode.speedbay.auction.application.controller;

import io.crowdcode.speedbay.auction.model.Message;
import io.crowdcode.speedbay.auction.service.ApplicationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
public class ApplicationLogController {

    @Autowired
    private ApplicationLogService logService;

    @PostMapping("/logs")
    public void logMessage(@RequestBody String message) {
        logService.log(message);
    }

//    @RequestMapping(path="/logs", method = RequestMethod.GET)
    @GetMapping("/logs")
    public List<Message> lastLogs() {
        return logService.lastLogs(Duration.of(30, ChronoUnit.SECONDS));
    }

}
