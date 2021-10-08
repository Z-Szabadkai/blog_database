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
    private List<Content> postList;

    public Blog(String title) {}

    public Blog (String title, BlogTemplate template) {
        this.title = title;
        this.template = template;
    }
}
