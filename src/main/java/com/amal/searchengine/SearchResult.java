package com.amal.searchengine;

public class SearchResult {

    private WebPage page;
    private int score;
    private String snippet;

    public SearchResult(WebPage page, int score, String snippet) {
        this.page = page;
        this.score = score;
        this.snippet = snippet;
    }

    public WebPage getPage() {
        return page;
    }

    public int getScore() {
        return score;
    }

    public String getSnippet() {
        return snippet;
    }
}