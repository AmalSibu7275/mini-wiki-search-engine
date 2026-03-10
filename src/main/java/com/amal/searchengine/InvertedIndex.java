package com.amal.searchengine;

import java.io.Serializable;
import java.util.*;

public class InvertedIndex implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, List<String>> index = new HashMap<>();

    public InvertedIndex() {
        index = new HashMap<>();
    }

    public List<String> getSuggestions(String prefix) {
        List<String> suggestions = new ArrayList<>();

        prefix = prefix.toLowerCase();

        for (String word : index.keySet()) {
            if (word.startsWith(prefix)) {
                suggestions.add(word);
            }
        }

        suggestions.sort(String::compareTo);

        if (suggestions.size() > 10) {
            return suggestions.subList(0, 10);
        }

        return suggestions;
    }
    
    public void addWord(String word, String url) {

        if (!index.containsKey(word)) {
            index.put(word, new ArrayList<>());
        }

        List<String> urls = index.get(word);

        if (!urls.contains(url)) {
            urls.add(url);
        }
    }

    public List<String> search(String word) {
        word = word.toLowerCase();

        if (index.containsKey(word)) {
            return index.get(word);
        }

        return new ArrayList<>();
    }

    public void printIndex() {
        for (String word : index.keySet()) {
            System.out.println(word + " -> " + index.get(word));
        }
    }
}