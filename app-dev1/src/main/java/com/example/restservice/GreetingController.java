package com.example.restservice;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@PropertySources({
        @PropertySource("file:${confDir:/etc/config}/application.properties"),
        @PropertySource("file:${confDir:/etc/config}/application-spec.properties")
})
public class GreetingController {
    private static final String template = "Hello, %s. Test config values: base- %s, spec- %s, over- %s !";
    private final AtomicLong counter = new AtomicLong();
    Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @Value("${base.prop1:undefined}")
    private String baseProp1;

    @Value("${env.prop1:undefined}")
    private String specProp1;

    @Value("${over.prop1:undefined}")
    private String overProp1;

    @Value("${dev2.url:app-dev1-port:8080/greeting}")
    private String dev2Url;

    private final HttpClient httpClient = new DefaultHttpClient();

    @PostConstruct
    public void init() {
        logger.info(makeMsg("TEST"));
    }

    private String makeMsg(String name) {
        return String.format(template, name, baseProp1, specProp1, overProp1);
    }

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) throws IOException, InterruptedException {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
        return new Greeting(counter.incrementAndGet(), makeMsg(name), getDev2Greeting());
    }

    @GetMapping("/test")
    public String test() {
        return "Test!!!";
    }

    private String getDev2Greeting() throws IOException, InterruptedException {

        HttpGet request = new HttpGet("http://" + dev2Url);
        HttpResponse response = httpClient.execute(request);
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        return rd.readLine();

    }}
