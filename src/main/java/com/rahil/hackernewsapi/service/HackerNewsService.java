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
import com.rahil.hackernewsapi.model.Story;
import com.rahil.hackernewsapi.model.StoryDetails;
import com.rahil.hackernewsapi.repository.StoryRepository;

@Service
public class HackerNewsService {

    private static final String TOP_STORIES_API_URL = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";
    private static final String STORY_API_URL = "https://hacker-news.firebaseio.com/v0/item/";
    private static final String COMMENT_API_URL = "https://hacker-news.firebaseio.com/v0/item/%s.json?print=pretty";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StoryRepository storyRepository;
    
    @Cacheable(value = "getTopStories", key = "'topStories'")
    public List<Long> getTopStories() {
        ResponseEntity<Long[]> response = restTemplate.getForEntity(TOP_STORIES_API_URL, Long[].class);
        return Arrays.asList(response.getBody());
    }


    @Cacheable(value = "storyDetails", key = "'storyDetails'")
    public List<StoryDetails> getTop10Stories() {
        List<Long> topStoryIds = getTopStories();
        List<StoryDetails> topStoryDetails = new ArrayList<>();
        for (int i = 0; i < 10 && i < topStoryIds.size(); i++) {
            ResponseEntity<StoryDetails> response = restTemplate.getForEntity(
                    STORY_API_URL + topStoryIds.get(i) + ".json", StoryDetails.class);
            StoryDetails storyDetails = response.getBody();
            Story story = new Story();
            story.setId(storyDetails.getId());
            story.setTitle(storyDetails.getTitle());
            story.setUrl(storyDetails.getUrl());
            story.setScore(storyDetails.getScore());
            story.setTime(storyDetails.getTime());
            story.setBy(storyDetails.getBy());
            storyRepository.save(story);
            topStoryDetails.add(storyDetails);
        }
        return topStoryDetails;
    }

    @Cacheable(value = "comments", key = "'comments'+#storyId")
    public List<Comment> getComments(String storyId) {
        RestTemplate restTemplate = new RestTemplate();
        StoryDetails storyDetails = restTemplate.getForObject(String.format(COMMENT_API_URL, storyId),
                StoryDetails.class);
        List<Integer> commentIds = storyDetails.getKids().subList(0, 10); // Get the first 10 comment IDs
        List<Comment> comments = new ArrayList<>();
        for (Integer commentId : commentIds) {
            Comment comment = restTemplate.getForObject(String.format(COMMENT_API_URL, commentId), Comment.class);
            if (comment != null) {
                comments.add(comment);
            }
        }
        return comments;
    }

    public List<StoryDetails> getPastStories() {
        List<Story> stories = storyRepository.findAll();
        List<StoryDetails> storyDetailsList = new ArrayList<>();
        for (Story story : stories) {
            StoryDetails storyDetails = new StoryDetails();
            storyDetails.setId(story.getId());
            storyDetails.setTitle(story.getTitle());
            storyDetails.setUrl(story.getUrl());
            storyDetails.setScore(story.getScore());
            storyDetails.setTime(story.getTime());
            storyDetails.setBy(story.getBy());
            storyDetailsList.add(storyDetails);
        }
        return storyDetailsList;
    }

}
