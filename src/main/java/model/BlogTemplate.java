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

    @Getter
    @Setter
    private templateStyle style;

    @Getter
    @Setter
    private templateColor color;

    public BlogTemplate() {

    }

    public BlogTemplate(String templateName, byte[] templateBackground, templateStyle style, templateColor color) {
        this.templateName = templateName;
        this.templateBackground = templateBackground;
        this.style = style;
        this.color = color;
    }
}
