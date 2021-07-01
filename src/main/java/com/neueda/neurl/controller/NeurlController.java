package com.neueda.neurl.controller;

import com.neueda.neurl.LongURLDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = "/neurl")
public class NeurlController {

    @PostMapping(path = "/short-me" )

    @ResponseBody
    public String shortMe(@RequestBody LongURLDto longURLDto){

        return "hello";
    }



}
