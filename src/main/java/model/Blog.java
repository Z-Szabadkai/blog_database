package model;

import lombok.Getter;
import lombok.Setter;

public class Blog extends User {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String blogName;

    @Getter
    @Setter
    private BlogTemplate template;

    public Blog() {
    }

    public Blog (long id, String blogName) {
        this.id = id;
        this.blogName = blogName;
    }

    public Blog (String userName, byte[] avatar, String blogName, BlogTemplate template) {
        super(userName, avatar);
        this.blogName = blogName;
        this.template = template;
    }
}
