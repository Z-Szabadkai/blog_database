package model;

import lombok.Getter;
import lombok.Setter;

public class BlogTemplate {

    @Getter
    @Setter
    private String templateName;

    @Getter
    @Setter
    private byte[] templateBackground;

    private templateStyle style;
    private templateColor color;

    public BlogTemplate(String templateName, byte[] templateBackground, templateStyle style, templateColor color) {
        this.templateName = templateName;
        this.templateBackground = templateBackground;
        this.style = style;
        this.color = color;
    }
}
