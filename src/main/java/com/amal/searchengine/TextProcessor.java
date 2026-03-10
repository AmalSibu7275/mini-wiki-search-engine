package com.amal.searchengine;

import java.util.ArrayList;
import java.util.List;

public class TextProcessor {

    public static List<String> extractWords(String text) {

        List<String> words = new ArrayList<>();

        text = text.toLowerCase();

        String[] tokens = text.split("\\W+");

        for (int i = 0; i < tokens.length; i++) {
        	String word = tokens[i];
            if (!word.isEmpty()) {
                words.add(word);
            }

        }

        return words;
    }
}