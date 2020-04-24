package com.example.restservice;

public class Greeting {
    private final long id;
    private final String content;
    private final String dev2Response;

    public Greeting(long id, String content, String dev2Response) {
        this.id = id;
        this.content = content;
        this.dev2Response = dev2Response;
    }

    public long getId() {
        return id;
    }

    public String getContent(){
        return content;
    }

    public String getDev2Response() {
        return dev2Response;
    }
}
