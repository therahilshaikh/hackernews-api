package com.rahil.hackernewsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahil.hackernewsapi.model.StoryDetails;

public interface StoryDetailsRepository extends JpaRepository<StoryDetails, Long> {

}
