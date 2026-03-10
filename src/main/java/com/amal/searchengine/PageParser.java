package com.amal.searchengine;

import org.jsoup.nodes.Document;

public class PageParser {

    public static WebPage parse(String url, Document doc) {

        if (doc == null) {
            return null;
        }

        String title = doc.title();
        String text = doc.select("p").text();

        return new WebPage(url, title, text);
    }
}