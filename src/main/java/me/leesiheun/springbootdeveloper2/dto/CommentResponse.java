package me.leesiheun.springbootdeveloper2.dto;

import lombok.Getter;
import me.leesiheun.springbootdeveloper2.domain.Comment;

@Getter
public class CommentResponse {
    private final String author;
    private final String content;

    public CommentResponse(Comment comment) {
        this.author = comment.getAuthor();
        this.content = comment.getContent();
    }
}
