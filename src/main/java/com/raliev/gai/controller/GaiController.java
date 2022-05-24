package com.raliev.gai.controller;

import com.raliev.gai.service.AbstractRegNumberGenerationService;
import com.raliev.gai.service.RegNumberExceedException;
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
    private AbstractRegNumberGenerationService randomGenerationService;

    @Autowired
    private AbstractRegNumberGenerationService sequentialGenerationService;

    @GetMapping(value = "/next", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getNext() {
        return sequentialGenerationService.generate().toString();
    }

    @GetMapping(value = "/random", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getRandom() {
        return randomGenerationService.generate().toString();
    }

    @ExceptionHandler(RegNumberExceedException.class)
    public String handleException() {
        return "Error: all possible variations of the numbers were returned.";
    }
}
