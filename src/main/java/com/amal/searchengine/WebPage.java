package com.amal.searchengine;

import java.io.Serializable;

public class WebPage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String url;
    private String title;
    private String text;

    public WebPage(String url, String title, String text) {
        this.url = url;
        this.title = title;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}