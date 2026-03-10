package com.amal.searchengine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchEngine {

	private InvertedIndex index;
	private List<WebPage> pages;

	public SearchEngine(InvertedIndex index, List<WebPage> pages) {
		this.index = index;
		this.pages = pages;
	}

	public List<String> getSuggestions(String prefix) {
	    return index.getSuggestions(prefix);
	}
	
	public List<SearchResult> search(String query) {

		String[] words = query.toLowerCase().split("\\W+");

		Set<String> resultUrls = null;

		for (String word : words) {
			List<String> urls = index.search(word);
			Set<String> urlSet = new HashSet<>(urls);

			if (resultUrls == null) {
				resultUrls = urlSet;
			} else {
				resultUrls.retainAll(urlSet);
			}
		}

		List<SearchResult> results = new ArrayList<>();

		if (resultUrls != null) {
			for (String url : resultUrls) {
				for (WebPage page : pages) {
					if (page.getUrl().equals(url)) {
						int score = calculateScore(page, words);
						String snippet = createSnippet(page.getText(), words);
						snippet = snippet.replaceAll("(?i)" + query, "<b>" + query + "</b>");
						results.add(new SearchResult(page, score, snippet));
					}
				}
			}
		}

		results.sort(Comparator.comparingInt(SearchResult::getScore).reversed());

		return results;
	}

	private int calculateScore(WebPage page, String[] words) {
		int score = 0;

		List<String> pageWords = TextProcessor.extractWords(page.getText());

		for (String searchWord : words) {
			for (String pageWord : pageWords) {
				if (pageWord.equals(searchWord)) {
					score++;
				}
			}
		}

		return score;
	}

	private String createSnippet(String text, String[] words) {

		if (text == null || text.isEmpty()) {
			return "";
		}

		text = text.trim();

		String snippet;

		if (text.length() <= 160) {
			snippet = text;
		} else {
			snippet = text.substring(0, 160);
		}

		for (String word : words) {
			snippet = snippet.replaceAll("(?i)\\b" + word + "\\b",
					"<b>" + word + "</b>");
		}

		return snippet + "...";
	}
}