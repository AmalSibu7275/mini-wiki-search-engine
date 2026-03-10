package com.amal.searchengine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PageFetcher {

    public static Document fetch(String url) {
        try {
            return Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(5000)
                    .get();
        } catch (Exception e) {
            System.out.println("Failed to fetch: " + url);
            return null;
        }
    }
}