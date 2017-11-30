package io.crowdcode.speedbay.auction.service;

import io.crowdcode.speedbay.auction.model.Message;

import java.time.Duration;
import java.util.List;

public interface ApplicationLogService {

    void log(String message, Object... args);

    List<Message> lastLogs(Duration duration);

}
