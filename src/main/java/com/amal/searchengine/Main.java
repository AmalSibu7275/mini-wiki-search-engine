package com.amal.searchengine;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static SearchEngine searchEngine;

	public static void main(String[] args) {

		System.out.println("Starting search engine...");
		System.out.println("pages.ser path: " + new java.io.File("pages.ser").getAbsolutePath());
		System.out.println("index.ser path: " + new java.io.File("index.ser").getAbsolutePath());

		List<WebPage> pages = null;
		InvertedIndex index = null;

		if (pages == null || index == null) {

			System.out.println("No saved data found.");
			System.out.println("Crawling pages...");

			Crawler crawler = new Crawler();
			pages = crawler.crawl("https://en.wikipedia.org/wiki/Computer_science");

			System.out.println("Building index...");

			index = new InvertedIndex();

			for (WebPage page : pages) {
				List<String> words = TextProcessor.extractWords(page.getText());

				for (String word : words) {
					index.addWord(word, page.getUrl());
				}
			}

			System.out.println("Saving data...");
			DataStore.savePages(pages);
			DataStore.saveIndex(index);

		} 
		System.out.println("Using saved pages and index.");

		searchEngine = new SearchEngine(index, pages);

		SpringApplication.run(Main.class, args);
	}
}