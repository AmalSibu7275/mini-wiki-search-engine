package com.amal.searchengine;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "query", required = false) String query, Model model) {

        List<SearchResult> results = new ArrayList<>();

        if (query != null && !query.trim().isEmpty()) {
            results = Main.searchEngine.search(query);
        }

        model.addAttribute("query", query);
        model.addAttribute("results", results);

        return "index";
    }

    @GetMapping("/suggest")
    @ResponseBody
    public List<String> suggest(@RequestParam(name = "term", required = false) String term) {

        if (term == null || term.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return Main.searchEngine.getSuggestions(term);
    }
}