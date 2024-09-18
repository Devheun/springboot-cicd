package me.leesiheun.springbootdeveloper2.config.error.exception;

import me.leesiheun.springbootdeveloper2.config.error.ErrorCode;

public class ArticleNotFoundException extends NotFoundException {
    public ArticleNotFoundException() {
        super(ErrorCode.ARTICLE_NOT_FOUND);
    }
}
