package me.leesiheun.springbootdeveloper2.controller;

import lombok.RequiredArgsConstructor;
import me.leesiheun.springbootdeveloper2.domain.Article;
import me.leesiheun.springbootdeveloper2.dto.ArticleListViewResponse;
import me.leesiheun.springbootdeveloper2.dto.ArticleViewResponse;
import me.leesiheun.springbootdeveloper2.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();

        model.addAttribute("articles", articles);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model, Principal principal) {
        Article article = blogService.findArticleById(id);
        model.addAttribute("article", new ArticleViewResponse(article));
        String username = principal != null ? principal.getName() : null;
        model.addAttribute("username", username);
        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findArticleById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }
}