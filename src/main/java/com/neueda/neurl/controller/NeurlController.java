package com.neueda.neurl.controller;

import com.neueda.neurl.LongURLDto;
import com.neueda.neurl.service.UrlService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = "/neurl")
public class NeurlController {

    private UrlService urlService;

    @PostMapping(path = "/short-me" )
    @ResponseBody
    public String shortMe(@RequestBody LongURLDto longURLDto){
        return urlService.toShortUrl(longURLDto);
    }

    @GetMapping(path = "/{shortUrl}")
    public ResponseEntity<Void> getMe(@PathVariable ("shortUrl") String shortUrl){
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlService.getLongUrl(shortUrl)))
                .build();
    }
}
