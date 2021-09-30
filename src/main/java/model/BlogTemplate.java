package model;

import lombok.Getter;
import lombok.Setter;

public class BlogTemplate {

    @Getter
    @Setter
    private String templateName;

    public enum templateStyle {
        BOOK,
        FOOD,
        MARKETING,
        MUSIC,
        NEWS,
        PERSONAL,
        TRAVEL
    }

    public enum templateColor {
        BROWN,
        GREEN,
        PURPLE,
        RED,
        YELLOW
    }

    @Getter
    @Setter
    private byte[] templateBackground;
}
