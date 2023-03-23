package com.rahil.hackernewsapi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rahil.hackernewsapi.model.Comment;
import com.rahil.hackernewsapi.model.StoryDetails;

@Service
public class HackerNewsService {

    private static final String TOP_STORIES_API_URL = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";
    private static final String ITEM_API_URL = "https://hacker-news.firebaseio.com/v0/item/";
    @Autowired
    private RestTemplate restTemplate;

    @Cacheable(value = "getTopStories", key = "'topStories'")
    public List<Long> getTopStories() {
        ResponseEntity<Long[]> response = restTemplate.getForEntity(TOP_STORIES_API_URL, Long[].class);
        return Arrays.asList(response.getBody());
    }

    // @Cacheable(value = "getTopStoriesDetails", key = "'topStoriesDetails'")
    public List<StoryDetails> getTopStoriesDetails() {
        List<Long> topStoryIds = getTopStories();
        List<StoryDetails> topStoryDetails = new ArrayList<>();
        for (int i = 0; i < 1 && i < topStoryIds.size(); i++) {
            ResponseEntity<StoryDetails> response = restTemplate.getForEntity(
                    ITEM_API_URL + topStoryIds.get(i) + ".json", StoryDetails.class);
            StoryDetails storyDetails = response.getBody();
            storyDetails.setCommentCount(storyDetails.getCommentCount());
            topStoryDetails.add(response.getBody());
        }
        return topStoryDetails;
    }

    public List<StoryDetails> getTop10Stories() {
        List<StoryDetails> top10Stories = new ArrayList<>();
        List<StoryDetails> topStoryDetails = getTopStoriesDetails();
        for (int i = 0; i < 1 && i < topStoryDetails.size(); i++) {
            top10Stories.add(topStoryDetails.get(i));
        }
        return top10Stories;
    }

    private static final String COMMENT_API_ENDPOINT = "https://hacker-news.firebaseio.com/v0/item/%s.json?print=pretty";

    @Cacheable(value = "comments", key = "'comments'")
    public List<Comment> getComments(String storyId) {
        RestTemplate restTemplate = new RestTemplate();
        StoryDetails storyDetails = restTemplate.getForObject(String.format(COMMENT_API_ENDPOINT, storyId), StoryDetails.class);
        List<Integer> commentIds = storyDetails.getKids().subList(0, 10); // Get the first 10 comment IDs
        List<Comment> comments = new ArrayList<>();
        for (Integer commentId : commentIds) {
            Comment comment = restTemplate.getForObject(String.format(COMMENT_API_ENDPOINT, commentId), Comment.class);
            if (comment != null) {
                comments.add(comment);
            }
        }
        return comments;
    }

}
