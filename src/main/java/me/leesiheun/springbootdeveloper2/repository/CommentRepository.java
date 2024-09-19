package me.leesiheun.springbootdeveloper2.repository;

import me.leesiheun.springbootdeveloper2.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
