package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Blog extends User {

    @Getter
    @Setter
    public long id;

    @Getter
    @Setter
    public String title;

    @Getter
    @Setter
    public Date published;

    @Getter
    @Setter
    public boolean canComment;


    public Blog(String name, byte[] avatar, long id, String title, Date published, boolean canComment) {
        super(name, avatar);
        this.id = id;
        this.title = title;
        this.published = published;
        this.canComment = canComment;
    }


}
