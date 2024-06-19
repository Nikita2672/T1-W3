package com.example.check.controller;

import com.metrics.common.annotations.LogRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleStarterController {

    @LogRequest
    @GetMapping("/check/{arg}")
    public String checkLog(@PathVariable("arg") String arg) {
        return arg;
    }
}
