package com.example.restservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@PropertySources({
        @PropertySource("file:${confDir:/etc/config}/application.properties"),
        @PropertySource("file:${confDir:/etc/config}/application-spec.properties")
})
public class GreetingController {
    private static final String template = "Hello from dev2 %d, %s. Test config values: base- %s, spec- %s, over- %s !";
    private final AtomicLong counter = new AtomicLong();
    Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @Value("${base.prop1:undefined}")
    private String baseProp1;

    @Value("${env.prop1:undefined}")
    private String specProp1;

    @Value("${over.prop1:undefined}")
    private String overProp1;

    @PostConstruct
    public void init() {
        logger.info(makeMsg("TEST"));
    }

    private String makeMsg(String name) {
        return String.format(template, counter.incrementAndGet(), name, baseProp1, specProp1, overProp1);
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
        return makeMsg(name);
    }
}
