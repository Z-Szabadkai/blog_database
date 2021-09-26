package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class Post extends Blog {

    @Getter
    @Setter
    public String title;

    @Getter
    @Setter
    public String content;

    @Getter
    @Setter
    public List<String> tags;

    @Getter
    @Setter
    public Date published;

    @Getter
    @Setter
    public boolean canComment;

    public Post(long id, String blogName, String title, String content, List<String> tags, Date published, boolean canComment) {
        super(id, blogName);
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.published = published;
        this.canComment = canComment;
    }
}
