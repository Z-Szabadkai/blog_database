package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class Content {

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
    private Date published;

    @Getter
    @Setter
    private boolean canComment;

    @Getter
    @Setter
    private List<Comment> commentList;

    public Content() {
    }

    public Content(String title, String post, String[] tags, boolean canComment) {
        this.title = title;
        this.post = post;
        this.tags = tags;
        this.canComment = canComment;
    }
}
