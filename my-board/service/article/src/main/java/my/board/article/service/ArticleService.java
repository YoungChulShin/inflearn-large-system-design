package my.board.article.service;

import my.board.article.service.response.ArticlePageResponse;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import my.board.article.entity.Article;
import my.board.article.repository.ArticleRepository;
import my.board.article.service.request.ArticleCreateRequest;
import my.board.article.service.request.ArticleUpdateRequest;
import my.board.article.service.response.ArticleResponse;
import my.board.common.snowflake.Snowflake;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final Snowflake snowflake = new Snowflake();
    private final ArticleRepository articleRepository;

    @Transactional
    public ArticleResponse create(ArticleCreateRequest request) {
      Article article = articleRepository.save(
          Article.create(
            snowflake.nextId(),
            request.title(),
            request.content(),
            request.boardId(),
            request.writerId()
      ));

      return ArticleResponse.from(article);
    }

    @Transactional
    public ArticleResponse update(Long articleId, ArticleUpdateRequest request) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        article.update(request.title(), request.content());

        return ArticleResponse.from(article);
    }

    public ArticleResponse read(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow();

        return ArticleResponse.from(article);  
    }

    @Transactional
    public void delete(Long articleId) {
        articleRepository.deleteById(articleId);
    }

    public ArticlePageResponse readAll(Long boardId, Long page, Long pageSize) {
        return ArticlePageResponse.of(
            articleRepository.findAll(boardId, (page - 1) * pageSize, pageSize).stream()
                .map(ArticleResponse::from)
                .toList(),
            articleRepository.count(
                boardId,
                PageLimitCalculator.calculatePageLimit(page, pageSize, 10L))
        );
    }

}
