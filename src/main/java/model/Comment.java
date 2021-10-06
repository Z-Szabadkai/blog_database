package model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Comment {

    @Getter
    @Setter
    String content;

    @Getter
    @Setter
    long commenterId;

    @Getter
    @Setter
    String creator;

    @Getter
    @Setter
    String commentPost;

    @Getter
    @Setter
    LocalDateTime commentTime;

    public Comment () {}

    public Comment (String content, long commenterId, String creator, String commentPost, LocalDateTime commentTime) {}
}

