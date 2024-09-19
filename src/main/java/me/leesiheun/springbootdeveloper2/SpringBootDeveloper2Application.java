package me.leesiheun.springbootdeveloper2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootDeveloper2Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloper2Application.class, args);
    }
}
