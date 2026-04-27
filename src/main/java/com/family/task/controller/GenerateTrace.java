package com.family.task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateTrace {

    @GetMapping("/test-trace")
    public String testTrace() {
        return "OK";
    }
}
