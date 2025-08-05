package my.board.common.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.board.common.event.payload.ArticleCreatedEventPayload;
import my.board.common.event.payload.ArticleDeletedEventPayload;
import my.board.common.event.payload.ArticleUpdatedEventPayload;

@Slf4j
@Getter
@RequiredArgsConstructor
public enum EventType {

  ARTICLE_CREATED(ArticleCreatedEventPayload.class, Topic.MY_BOARD_ARTICLE),
  ARTICLE_UPDATED(ArticleUpdatedEventPayload.class, Topic.MY_BOARD_ARTICLE),
  ARTICLE_DELETED(ArticleDeletedEventPayload.class, Topic.MY_BOARD_ARTICLE);

  private final Class<? extends EventPayload> payloadClass;
  private final String topic;

  public static EventType from(String type) {
    try {
      return EventType.valueOf(type);
    } catch (Exception e) {
      return null;
    }
  }

  public static class Topic {
    public static final String MY_BOARD_ARTICLE = "my-board-article";
  }

}
