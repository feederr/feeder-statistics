package org.feeder.api.statistics.item.statistics.event;

import static org.feeder.api.statistics.common.KafkaTopics.GROUP_ID;
import static org.feeder.api.statistics.common.KafkaTopics.ITEM_REMOVED_TOPIC;
import static org.feeder.api.statistics.common.KafkaTopics.ITEM_VIEWED_TOPIC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feeder.api.statistics.item.statistics.service.ItemStatisticsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemStatisticsListener {

  private final ItemStatisticsService service;

  @Value(ITEM_VIEWED_TOPIC)
  private String itemViewedTopic;

  @Value(ITEM_REMOVED_TOPIC)
  private String itemRemovedTopic;

  @KafkaListener(topics = ITEM_VIEWED_TOPIC, groupId = GROUP_ID)
  public void listenItemViewedEvent(ItemViewedEvent event) {

    log.debug(
        "{} consumed {} from topic: \n{}",
        ItemStatisticsListener.class.getSimpleName(),
        event,
        itemViewedTopic
    );

    service.updateViews(event);
  }

  @KafkaListener(topics = ITEM_REMOVED_TOPIC, groupId = GROUP_ID)
  public void listenItemRemovedEvent(ItemRemovedEvent event) {

    log.debug(
        "{} consumed {} from topic: \n{}",
        ItemStatisticsListener.class.getSimpleName(),
        event,
        itemRemovedTopic
    );

    service.removeStatistics(event);
  }
}
