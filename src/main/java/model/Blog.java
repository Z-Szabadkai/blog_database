package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Blog {

    @Getter
    @Setter
    private long blogId;

    @Getter
    @Setter
    private String blogName;

    @Getter
    @Setter
    private BlogTemplate template;

    @Getter
    @Setter
    public List<Content> postList;

    public Blog() {}

    public Blog (String blogName, BlogTemplate template) {
        this.blogName = blogName;
        this.template = template;
    }

    public Blog (long blogId, String blogName, BlogTemplate template) {
        this(blogName, template);
        this.blogId= blogId;
    }
}
