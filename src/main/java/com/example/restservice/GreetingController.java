package com.example.restservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s! From buzzer %s!";
    private static final String message_template = "%s from buzzer %s!";
    @Value("${buzzer:unknown}")
    private String buzzerId;
    private final AtomicLong counter = new AtomicLong();
    Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        logger.trace(String.format(message_template, "A TRACE Message", buzzerId));
        logger.debug(String.format(message_template, "A TRACE Message", buzzerId));
        logger.info(String.format(message_template, "A TRACE Message", buzzerId));
        logger.warn(String.format(message_template, "A TRACE Message", buzzerId));
        logger.error(String.format(message_template, "A TRACE Message", buzzerId));
        return new Greeting(counter.incrementAndGet(), String.format(template, name, buzzerId));
    }
}
