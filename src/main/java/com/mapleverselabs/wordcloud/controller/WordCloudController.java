package com.mapleverselabs.wordcloud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")
public class WordCloudController {

    private Map<String, Integer> wordFrequency = new ConcurrentHashMap<>();

    @PostMapping("/submit-word")
    public ResponseEntity<String> submitWord(@RequestBody Map<String, String> request) {
        String word = request.get("word").toLowerCase();
        wordFrequency.merge(word, 1, Integer::sum);
        return ResponseEntity.ok("Word submitted successfully!");
    }

    @GetMapping("/word-cloud")
    public ResponseEntity<Map<String, Integer>> getWordCloud() {
        return ResponseEntity.ok(wordFrequency);
    }
}