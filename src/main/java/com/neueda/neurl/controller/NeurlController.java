package com.neueda.neurl.controller;

import com.neueda.neurl.LongURLDto;
import com.neueda.neurl.service.UrlService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


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
    @ResponseBody
    public String getMe(@PathVariable ("shortUrl") String shortUrl){
        return urlService.getLongUrl(shortUrl);
    }
}
