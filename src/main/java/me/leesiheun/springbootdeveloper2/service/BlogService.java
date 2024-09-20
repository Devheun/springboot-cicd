package me.leesiheun.springbootdeveloper2.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.leesiheun.springbootdeveloper2.config.error.exception.ArticleNotFoundException;
import me.leesiheun.springbootdeveloper2.config.error.exception.CommentNotFoundException;
import me.leesiheun.springbootdeveloper2.domain.Article;
import me.leesiheun.springbootdeveloper2.domain.Comment;
import me.leesiheun.springbootdeveloper2.dto.AddArticleRequest;
import me.leesiheun.springbootdeveloper2.dto.AddCommentRequest;
import me.leesiheun.springbootdeveloper2.dto.UpdateArticleRequest;
import me.leesiheun.springbootdeveloper2.repository.BlogRepository;
import me.leesiheun.springbootdeveloper2.repository.CommentRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    public Article save(AddArticleRequest request, String userName) {
        return blogRepository.save(request.toEntity(userName));
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findArticleById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(ArticleNotFoundException::new);
    }

    public Comment findCommentById(long id) {
        return commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);
    }

    public void delete(long id) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        authorizeArticleAuthor(article);
        blogRepository.delete(article);
    }

    @Transactional
    public Article updateArticle(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        authorizeArticleAuthor(article);
        article.update(request.getTitle(), request.getContent());

        return article;
    }

    // 게시글을 작성한 유저인지 확인
    private static void authorizeArticleAuthor(Article article) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!article.getAuthor().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }

    public Comment addComment(AddCommentRequest request, String userName) {
        Article article = blogRepository.findById(request.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("not found : " + request.getArticleId()));
        return commentRepository.save(request.toEntity(userName, article));
    }

//    @Transactional
//    public Comment updateComment(long id, UpdateCommentRequest request) {
//        Comment comment = commentRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
//    }
}