package com.raliev.gai.controller;

import com.raliev.gai.repositories.NoElementException;
import com.raliev.gai.service.provider.RegNumberProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/number")
public class GaiController {

    @Autowired
    private RegNumberProvider sequentialProvider;

    @Autowired
    private RegNumberProvider randomProvider;

    @GetMapping(value = "/next", produces = MediaType.TEXT_PLAIN_VALUE)
    public String next() {
        return sequentialProvider.provide();
    }

    @GetMapping(value = "/random", produces = MediaType.TEXT_PLAIN_VALUE)
    public String random() {
        return randomProvider.provide();
    }

    @ExceptionHandler(NoElementException.class)
    public String handleException() {
        return "Error: all possible variations of the numbers were returned.";
    }
}
