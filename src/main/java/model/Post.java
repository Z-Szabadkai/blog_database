package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class Post extends Blog {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private List<String> tags;

    @Getter
    @Setter
    private Date published;

    @Getter
    @Setter
    private boolean canComment;

    public Post(long id, String blogName, String title, String content, List<String> tags, Date published, boolean canComment) {
        super(id, blogName);
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.published = published;
        this.canComment = canComment;
    }
}
