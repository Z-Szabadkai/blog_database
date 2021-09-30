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

    public Blog (long id, String blogName) {
        this.id = id;
        this.blogName = blogName;
    }

    public Blog (String userName, byte[] avatar, long id, String blogName) {
        super(userName, avatar);
        this.id = id;
        this.blogName = blogName;
    }
}
