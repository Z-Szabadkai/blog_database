package model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Comment {

    @Getter
    @Setter
    long comment_id;

    @Getter
    @Setter
    String comment_post;

    @Getter
    @Setter
    LocalDateTime comment_date;

    public Comment () {}

    public Comment (long comment_id, String comment_post, LocalDateTime comment_date) {
        this.comment_id = comment_id;
        this.comment_post = comment_post;
        this.comment_date = comment_date;
    }

}

