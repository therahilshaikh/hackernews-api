package com.rahil.hackernewsapi.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Story implements Serializable{
    private Integer id;
    private String by;
    private int score;
    private String time;
    private String title;
    private String url;
    private List<Integer>kids;

}
