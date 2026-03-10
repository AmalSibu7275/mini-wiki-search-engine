package com.amal.searchengine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {

    public List<WebPage> crawl(String startUrl) {

        int maxPages = 100;

        Queue<String> pagesToVisit = new LinkedList<>();
        Set<String> visitedPages = new HashSet<>();
        List<WebPage> savedPages = new ArrayList<>();

        pagesToVisit.add(startUrl);

        while (!pagesToVisit.isEmpty() && visitedPages.size() < maxPages) {

            String url = pagesToVisit.poll();

            if (url == null) {
                continue;
            }

            if (visitedPages.contains(url)) {
                continue;
            }

            Document doc = PageFetcher.fetch(url);

            if (doc == null) {
                continue;
            }

            visitedPages.add(url);

            WebPage page = PageParser.parse(url, doc);

            if (page != null) {
                savedPages.add(page);

                System.out.println("\nProgress: " + visitedPages.size() + "/" + maxPages);
                System.out.println("Visited: " + page.getUrl());
                System.out.println("Title: " + page.getTitle());
                System.out.println("Text length: " + page.getText().length());
            }

            Elements links = doc.select("a[href]");

            for (Element link : links) {
                String nextUrl = link.attr("abs:href");

                if (nextUrl.isEmpty()) {
                    continue;
                }

                int hashIndex = nextUrl.indexOf("#");
                if (hashIndex != -1) {
                    nextUrl = nextUrl.substring(0, hashIndex);
                }

                if (!nextUrl.startsWith("https://en.wikipedia.org/wiki/")) {
                    continue;
                }

                String articlePart = nextUrl.substring("https://en.wikipedia.org/wiki/".length());

                if (articlePart.contains(":")) {
                    continue;
                }
                
                if (articlePart.contains("disambiguation")) {
                	continue;
                }
                
                if (articlePart.startsWith("List_of")) {
                	continue;
                }
                
                if (articlePart.startsWith("Timeline_of")) {
                	continue;
                }

                if (articlePart.contains("?")) {
                    continue;
                }

                if (!visitedPages.contains(nextUrl) && !pagesToVisit.contains(nextUrl)) {
                    pagesToVisit.add(nextUrl);
                }
            }
        }

        System.out.println("\nCrawling finished.");
        System.out.println("Visited pages: " + savedPages.size());

        return savedPages;
    }
}