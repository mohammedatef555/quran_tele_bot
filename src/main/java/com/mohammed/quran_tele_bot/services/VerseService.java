package com.mohammed.quran_tele_bot.services;

import com.mohammed.quran_tele_bot.models.Verse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class VerseService {
    private static final Logger logger = LoggerFactory.getLogger(VerseService.class);

    private final RestTemplate restTemplate;
    private int min = 2;
    private int max = 6236;

    public VerseService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public String getRandomVerse() {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        String url = "http://api.alquran.cloud/v1/ayah/" + randomNum;
        try {
            Verse verse = restTemplate.getForObject(url, Verse.class);
            if (verse != null) {
                int length = verse.getData().getText().length();
                if ( length < 10 || length > 200 ){
                    return getRandomVerse();
                }
                logger.info(verse.toString());
                return verse.getData().getText();
            }
        } catch (RestClientException exception){
            logger.error(exception.getLocalizedMessage());
        }
        return null;
    }

}
