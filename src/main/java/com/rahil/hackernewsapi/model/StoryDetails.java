package com.rahil.hackernewsapi.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoryDetails implements Serializable {
    private long id;
    private String title;
    private String url;
    private int score;
    private String time;
    private String by;
    private List<Integer> kids;

}
