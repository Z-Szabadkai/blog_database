package model;

import lombok.Getter;
import lombok.Setter;

public class Comment extends Post {

    @Getter
    @Setter
    String content;

    public Comment() {

    }
}
