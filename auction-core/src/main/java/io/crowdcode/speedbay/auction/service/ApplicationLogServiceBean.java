package io.crowdcode.speedbay.auction.service;


import io.crowdcode.speedbay.auction.model.Message;
import io.crowdcode.speedbay.auction.repository.ApplicationLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ApplicationLogServiceBean implements ApplicationLogService {

    @Autowired
    private ApplicationLogRepository logRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log(String message, Object... args) {
        String msg = String.format(message, args);
        String username = "System";
        logRepository.log(msg, LocalDateTime.now(), username);
    }

//    public void updateMessage(String createdBy) {
//        Message message = logRepository.getLastMessage();
//        message.setCreatedBy(createdBy);
//
//        // rollback der Transaktion
//
//        //Welcher Wert steht in Message.getCreatedBy? Parameter Wert
//    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Message> lastLogs(Duration duration) {
        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = to.minus(duration);

        return logRepository.findLogs(from, to);
    }
}
