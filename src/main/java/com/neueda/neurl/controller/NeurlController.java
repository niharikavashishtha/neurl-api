package com.neueda.neurl.controller;

import com.neueda.neurl.dto.LongUrlDTO;
import com.neueda.neurl.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "/neurl")
public class NeurlController {

    private final UrlService urlService;

    @Value("${neurl.api.longUrlMaxSize:2097152}")
    private int maxURLSize;

    @PostMapping(path = "/short-me" )
    @ResponseBody
    public ResponseEntity<String> shortMe(@RequestBody LongUrlDTO longURLDto) {
        if (!StringUtils.hasText(longURLDto.getLongUrl())) {
            log.error("Invalid Input Passed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (longURLDto.getLongUrl().getBytes().length > maxURLSize) {
            log.error("Long URL is longer than {}", maxURLSize);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        log.info("Received URL shortening request for {}", longURLDto.getLongUrl());
        return ResponseEntity.status(HttpStatus.OK).body("http://localhost:8080/neurl/" + urlService.toShortUrl(longURLDto));
    }

    @Cacheable(value = "urls", key = "#shortUrl", sync = true)
    @GetMapping(path = "/{shortUrl}")
    public ResponseEntity<Void> getMe(@PathVariable ("shortUrl") String shortUrl) {
        if (!StringUtils.hasText(shortUrl)) {
            log.error("Invalid Input Passed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        log.info("Received URL get request for short url {}", shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlService.getLongUrl(shortUrl)))
                .build();
    }
}