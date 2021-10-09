package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Blog {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private BlogTemplate template;

    @Getter
    @Setter
    private List<Content> contentList;

    public Blog(String title) {
        this.title = title;
    }
}
