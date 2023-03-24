package com.rahil.hackernewsapi.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment implements Serializable{
    private String text;
    private String by;
}
