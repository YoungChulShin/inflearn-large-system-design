package my.board.article.api;

import my.board.article.service.request.ArticleCreateRequest;
import my.board.article.service.response.ArticleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

public class ArticleApiTest {

  RestClient restClient = RestClient.create("http://localhost:9000");

  @Test
  void createTest() {
    ArticleResponse articleResponse = create(new ArticleCreateRequest("hi", "my content", 1L, 1L));
    System.out.println("resonse: " + articleResponse);
  }

  private ArticleResponse create(ArticleCreateRequest request) {
    return restClient.post()
      .uri("/v1/articles")
      .body(request)
      .retrieve()
      .body(ArticleResponse.class);
  }

}
