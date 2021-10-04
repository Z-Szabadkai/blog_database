package model;

import lombok.Getter;
import lombok.Setter;

public class BlogTemplate {


    @Getter
    @Setter
    private byte[] templateBackground;

    @Getter
    @Setter
    private templateStyle style;

    @Getter
    @Setter
    private templateColor color;

    public BlogTemplate() {

    }

    public BlogTemplate(byte[] templateBackground, templateStyle style, templateColor color) {
        this.templateBackground = templateBackground;
        this.style = style;
        this.color = color;
    }
}
