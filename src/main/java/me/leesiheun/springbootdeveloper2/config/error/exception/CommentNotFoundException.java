package me.leesiheun.springbootdeveloper2.config.error.exception;

import me.leesiheun.springbootdeveloper2.config.error.ErrorCode;

public class CommentNotFoundException extends NotFoundException {
    public CommentNotFoundException() {
        super(ErrorCode.COMMENT_NOT_FOUND);
    }
}
