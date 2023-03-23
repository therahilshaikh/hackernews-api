package com.rahil.hackernewsapi.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoryDetails implements Serializable{
    private String title;
    private String url;
    private int score;
    private long time;
    private String by;
    private List<Integer> kids;
    @Setter
    private int commentCount;


    public int getCommentCount() {
        if (kids == null) {
            return 0;
        } else {
            return kids.size();
        }
    }

    public String getTime() {
        Date date = new Date(time * 1000L); // Convert seconds to milliseconds
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // Date and time format
        return dateFormat.format(date);
    }

}
