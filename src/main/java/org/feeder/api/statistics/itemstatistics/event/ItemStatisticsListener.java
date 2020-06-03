package org.feeder.api.statistics.itemstatistics.event;

import static org.feeder.api.statistics.common.KafkaTopics.GROUP_ID;
import static org.feeder.api.statistics.common.KafkaTopics.ITEM_VIEWED_TOPIC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feeder.api.statistics.itemstatistics.service.ItemStatisticsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemStatisticsListener {

  private final ItemStatisticsService itemStatisticsService;

  @Value(ITEM_VIEWED_TOPIC)
  private String itemViewedTopic;

  @KafkaListener(topics = ITEM_VIEWED_TOPIC, groupId = GROUP_ID)
  public void listItemViewedEvent(ItemViewedEvent event) {

    log.debug(
        "{} consumed {} from topic: \n{}",
        ItemStatisticsListener.class.getSimpleName(),
        event,
        itemViewedTopic
    );

    itemStatisticsService.updateStatistics(event);
  }
}
