package model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class Content {

    public enum postStatus {DRAFT, PENDING, PUBLISHED, FUTURE}

    @Setter
    @Getter
    private long contentId;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String post;

    @Getter
    @Setter
    private String[] tags;

    @Getter
    @Setter
    private postStatus status;

    @Getter
    @Setter
    private LocalDateTime published_date;

    @Getter
    @Setter
    private boolean can_comment_under;

    @Getter
    @Setter
    private List<Comment> commentList;

    public Content(String title) {
        this.title = title;
    }

    public Content(String title, String post, String[] tags, postStatus status, LocalDateTime published_date, boolean can_comment_under) {
        this.title = title;
        this.post = post;
        this.tags = tags;
        this.status = status;
        this.published_date = published_date;
        this.can_comment_under = can_comment_under;
    }
}
