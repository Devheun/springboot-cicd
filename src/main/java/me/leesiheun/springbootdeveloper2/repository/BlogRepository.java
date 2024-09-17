package me.leesiheun.springbootdeveloper2.repository;

import me.leesiheun.springbootdeveloper2.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {

}