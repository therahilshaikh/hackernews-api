package com.rahil.hackernewsapi.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoryDetails implements Serializable {
    @Id
    private Integer id;
    private String by;
    private int score;
    private String time;
    private String title;
    private String url;

}
