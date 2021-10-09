package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Blog {

    private enum templateStyle {BOOK, FOOD,	MARKETING, MUSIC, NEWS, PERSONAL, TRAVEL}
    private enum templateColor {BLACK, BROWN, GREEN, PURPLE, RED, YELLOW}

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private byte[] templateBackground;

    @Getter
    @Setter
    private templateStyle templateStyle;

    @Getter
    @Setter
    private templateColor templateColor;


    @Getter
    @Setter
    private List<Content> contentList;

    public Blog(String title) {
        this.title = title;
    }
}
