package my.board.article.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import my.board.article.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArticleRepositoryTest {

  @Autowired
  ArticleRepository articleRepository;

  @Test
  void findAllTest() {
    List<Article> articles = articleRepository.findAll(1L, 100L, 30L);
  }

  @Test
  void countTest() {
    Long count = articleRepository.count(1L, 100L);
  }
}