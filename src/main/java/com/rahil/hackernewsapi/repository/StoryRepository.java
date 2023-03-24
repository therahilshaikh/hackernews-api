package com.rahil.hackernewsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahil.hackernewsapi.model.Story;

public interface StoryRepository extends JpaRepository<Story, Long> {

    List<Story> findTop10ByOrderByScoreDesc();
}
