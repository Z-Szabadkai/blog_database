package model;

import lombok.Getter;
import lombok.Setter;

public class Blog extends User {

    @Getter
    @Setter
    public long id;

    @Getter
    @Setter
    public String blogName;

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
