package me.leesiheun.springbootdeveloper2.controller;

import lombok.RequiredArgsConstructor;
import me.leesiheun.springbootdeveloper2.domain.Article;
import me.leesiheun.springbootdeveloper2.domain.Comment;
import me.leesiheun.springbootdeveloper2.dto.*;
import me.leesiheun.springbootdeveloper2.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody @Validated AddArticleRequest request, Principal principal) {
        Article savedArticle = blogService.save(request, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findArticleById(id);
        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);
        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.updateArticle(id, request);
        return ResponseEntity.ok()
                .body(updatedArticle);
    }

    @GetMapping("/api/comments/{id}")
    public ResponseEntity<CommentResponse> findComment(@PathVariable long id) {
        Comment comment = blogService.findCommentById(id);
        return ResponseEntity.ok()
                .body(new CommentResponse(comment));
    }


    @PostMapping("/api/comments")
    public ResponseEntity<AddCommentResponse> addComment(@RequestBody AddCommentRequest request, Principal principal) {
        Comment savedComment = blogService.addComment(request, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AddCommentResponse(savedComment));

    }

//    @PutMapping("/api/comments/{id}")
//    public ResponseEntity<Comment> updateComment(@PathVariable long id, @RequestBody UpdateCommentRequest request) {
//        Comment updatedComment = blogService.updateComment(id, request);
//        return ResponseEntity.ok()
//                .body(updatedComment);
//    }
}