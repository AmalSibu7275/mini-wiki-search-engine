# MiniWiki Search Engine

This project is a simple search engine written in Java. The goal was to understand how search engines work internally by building one from scratch.

The program crawls Wikipedia pages, extracts the text, builds an inverted index, and allows users to search through the indexed pages using a web interface.

## Features

- Web crawler that collects Wikipedia pages
- HTML parsing using Jsoup
- Inverted index for fast search
- Ranked search results
- Snippet generation for search results
- Autocomplete suggestions
- Web interface using Spring Boot and Thymeleaf
- Local data persistence for faster startup

## Technologies Used

- Java
- Spring Boot
- Thymeleaf
- Jsoup
- HTML / CSS
- Maven

## How It Works

1. The crawler starts from a Wikipedia page.
2. It visits multiple linked pages and extracts the text content.
3. The text is processed into words.
4. Words are stored in an inverted index mapping words to pages.
5. When a user searches, the engine finds pages containing those words.
6. Results are ranked and displayed with snippets.

   ## Project Structure

src/main/java/com/amal/searchengine

Crawler.java           → Crawls Wikipedia pages

PageFetcher.java       → Downloads HTML pages

PageParser.java        → Extracts page title and text

TextProcessor.java     → Processes text into searchable words

InvertedIndex.java     → Stores word → page mapping

SearchEngine.java      → Handles ranking and search

SearchController.java  → Web controller for search requests

Main.java              → Starts the Spring Boot application

## Running the Project

1. Clone the repository
  git clone https://github.com/AmalSibu7275/mini-wiki-search-engine.git

2. Open the project in Eclipse or IntelliJ.

3. Run `Main.java`.

4. Open the browser and go to:
  http://localhost:8080

## Future Improvements

- Crawl more pages
- Improve ranking algorithm
- Add pagination
- Improve snippet generation
