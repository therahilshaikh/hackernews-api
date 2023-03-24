package com.rahil.hackernewsapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rahil.hackernewsapi.model.Comment;
import com.rahil.hackernewsapi.model.StoryDetails;
import com.rahil.hackernewsapi.service.HackerNewsService;

@RestController
public class HackerNewsController {

    @Autowired
    private HackerNewsService hackerNewsService;

    @GetMapping("/top-stories")
    public List<StoryDetails> getTopStoriesDetails() {
        return hackerNewsService.getTop10Stories();
    }

    @GetMapping("/past-stories")
    public List<StoryDetails> getAllStories() {
        return hackerNewsService.getPastStories();
    }

    @GetMapping("/comments/{storyId}")
    public List<Comment> getTopStoriesDetails(@PathVariable String storyId) {
        System.out.println(storyId);
        return hackerNewsService.getComments(storyId);
    }
}
