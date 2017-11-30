package io.crowdcode.speedbay.auction.service;

import io.crowdcode.speedbay.auction.exception.BadWordException;
import io.crowdcode.speedbay.auction.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.crowdcode.speedbay.common.AnsiColor.green;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Slf4j
public class BadWordValidatorBean implements BadWordValidator {

    @Value("classpath:badWords.txt")
    private Resource badWordsFile;

    private List<String> badWords;

//    public BadWordValidatorBean(@Value("classpath:badWords.txt") Resource badWordsFile) {
//        this.badWordsFile = badWordsFile;
//        init();
//    }

//    public BadWordValidatorBean() {
//        init();
//    }

    @PostConstruct
    public void init() {
        log.info(green("init bad word validate"));

        try (InputStream is = badWordsFile.getInputStream();
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader bufferedReader = new BufferedReader(isr)) {

            badWords = bufferedReader
                    .lines()
                    .map(line -> line.split("\\,|\\s+"))
                    .flatMap(Arrays::stream)
                    .distinct()
                    .collect(Collectors.toList());
            log.info("found bad words: {}", Arrays.toString(this.badWords.toArray()));

        } catch (IOException e) {
            throw new SystemException("Could not read BadWords", e);
        }
    }

    @Override
    public void checkBadWords(String title) throws BadWordException {
        if (isInvalid(title)) {
            throw new BadWordException(title);
        }
    }

    @Override
    public boolean isInvalid(String title) {
        if (title != null && badWords != null) {
            for (String badWord : badWords) {
                if (title.contains(badWord)) {
                    return true;
                }
            }
        }
        return false;
    }

}
